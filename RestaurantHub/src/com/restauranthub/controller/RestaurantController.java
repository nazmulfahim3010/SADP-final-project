package com.restauranthub.controller;

import com.restauranthub.behavioral.command.OrderCommand;
import com.restauranthub.behavioral.command.OrderInvoker;
import com.restauranthub.behavioral.command.PlaceOrderCommand;
import com.restauranthub.behavioral.observer.OrderSubject;
import com.restauranthub.behavioral.strategy.HappyHourPricing;
import com.restauranthub.behavioral.strategy.PricingContext;
import com.restauranthub.behavioral.strategy.PricingStrategy;
import com.restauranthub.behavioral.strategy.RegularPricing;
import com.restauranthub.behavioral.state.OrderContext;
import com.restauranthub.creational.abstractfactory.PayPalFactory;
import com.restauranthub.creational.abstractfactory.PaymentGatewayFactory;
import com.restauranthub.creational.abstractfactory.PaymentProcessor;
import com.restauranthub.creational.abstractfactory.ReceiptGenerator;
import com.restauranthub.creational.abstractfactory.StripeFactory;
import com.restauranthub.creational.abstractfactory.TransactionValidator;
import com.restauranthub.creational.builder.Order;
import com.restauranthub.creational.builder.OrderBuilder;
import com.restauranthub.creational.factory.FoodItem;
import com.restauranthub.creational.factory.FoodItemFactory;
import com.restauranthub.creational.singleton.DatabaseConnection;
import com.restauranthub.creational.singleton.RestaurantConfig;
import com.restauranthub.gui.GuiObserver;
import com.restauranthub.gui.UiLog;
import com.restauranthub.models.Customer;
import com.restauranthub.models.FoodMenu;
import com.restauranthub.models.Receipt;
import com.restauranthub.structural.adapter.LegacyPaymentSystem;
import com.restauranthub.structural.adapter.ModernPaymentInterface;
import com.restauranthub.structural.adapter.PaymentAdapter;
import com.restauranthub.structural.decorator.BaseFoodItem;
import com.restauranthub.structural.decorator.CheeseDecorator;
import com.restauranthub.structural.decorator.ExtraPackagingDecorator;
import com.restauranthub.structural.decorator.FoodItemComponent;
import com.restauranthub.structural.facade.OrderProcessingFacade;
import com.restauranthub.structural.proxy.AdminOperations;
import com.restauranthub.structural.proxy.AdminProxy;
import java.util.List;

public class RestaurantController {
    private final UiLog uiLog;
    private final FoodMenu foodMenu;
    private final Receipt receipt;
    private DatabaseConnection databaseConnection;
    private RestaurantConfig restaurantConfig;
    private Customer currentCustomer;
    private Order currentOrder;
    private OrderContext orderContext;
    private OrderSubject orderSubject;
    private double finalPrice;
    private String currentOrderId;
    private String lastReceiptText;

    public RestaurantController(UiLog uiLog) {
        this.uiLog = uiLog;
        this.foodMenu = new FoodMenu();
        this.receipt = new Receipt();
    }

    public void initializeSystem() {
        databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.connect();
        restaurantConfig = RestaurantConfig.getInstance();
        uiLog.append("[Singleton] Database connection initialized.");
        uiLog.append("[Singleton] Restaurant: " + restaurantConfig.getRestaurantName());
        uiLog.append("[Singleton] Accepting orders: " + restaurantConfig.isAcceptingOrders());
    }

    public List<String> getMenuItems() {
        return foodMenu.getAvailableItems();
    }

    public String getMenuText() {
        return foodMenu.buildMenuText();
    }

    public Order buildOrder(
            String customerName,
            String phoneNumber,
            String selectedItem,
            boolean extraCheese,
            boolean extraPackaging,
            String orderType,
            String specialInstructions,
            String strategyName) {
        validateCustomer(customerName, phoneNumber);
        if (selectedItem == null || selectedItem.trim().isEmpty()) {
            throw new IllegalArgumentException("Please select a food item.");
        }

        currentCustomer = new Customer(customerName.trim(), phoneNumber.trim());
        currentOrderId = "ORD-" + (System.currentTimeMillis() % 100000);
        uiLog.append("[Models] Customer created: " + currentCustomer);

        FoodItem baseItem = FoodItemFactory.createFoodItem(selectedItem.toLowerCase());
        baseItem.prepare();
        uiLog.append("[Factory] Created " + baseItem.getName() + " at $" + String.format("%.2f", baseItem.getPrice()));

        FoodItemComponent decoratedItem = new BaseFoodItem(baseItem);
        if (extraCheese) {
            decoratedItem = new CheeseDecorator(decoratedItem);
            uiLog.append("[Decorator] Added extra cheese.");
        }
        if (extraPackaging) {
            decoratedItem = new ExtraPackagingDecorator(decoratedItem);
            uiLog.append("[Decorator] Added premium packaging.");
        }

        FoodItem orderItem = new DecoratedFoodItemAdapter(decoratedItem);
        currentOrder = new OrderBuilder()
                .setOrderType(orderType)
                .setSpecialInstructions(specialInstructions == null ? "" : specialInstructions.trim())
                .addItem(orderItem)
                .build();
        uiLog.append("[Builder] Order built successfully.");

        PricingContext pricingContext = new PricingContext(resolveStrategy(strategyName));
        finalPrice = pricingContext.calculateFinalPrice(currentOrder.getTotalPrice());
        uiLog.append("[Strategy] Final price calculated: $" + String.format("%.2f", finalPrice));

        orderContext = new OrderContext();
        orderSubject = new OrderSubject(currentOrderId);
        orderSubject.registerObserver(new GuiObserver("Customer", uiLog));
        orderSubject.registerObserver(new GuiObserver("Kitchen", uiLog));
        uiLog.append("[Observer] GUI observers registered for order " + currentOrderId + ".");
        uiLog.append("[State] Initial state: " + orderContext.getCurrentStatusText());

        lastReceiptText = null;
        return currentOrder;
    }

    public String processOrder(String gatewayName) {
        ensureOrderExists();

        OrderInvoker invoker = new OrderInvoker();
        OrderCommand placeOrderCommand = new PlaceOrderCommand(currentOrder);
        invoker.executeCommand(placeOrderCommand);
        uiLog.append("[Command] Order placement command executed.");

        OrderProcessingFacade facade = new OrderProcessingFacade();
        facade.processOrder(finalPrice);
        uiLog.append("[Facade] Payment, inventory, and kitchen flow completed.");

        PaymentGatewayFactory factory = resolveGateway(gatewayName);
        PaymentProcessor processor = factory.createProcessor();
        TransactionValidator validator = factory.createValidator();
        ReceiptGenerator receiptGenerator = factory.createReceiptGenerator();
        if (validator.validate()) {
            processor.processPayment(finalPrice);
            receiptGenerator.generateReceipt();
            uiLog.append("[Abstract Factory] " + gatewayName + " gateway family used successfully.");
        } else {
            throw new IllegalStateException("Transaction validation failed.");
        }

        ModernPaymentInterface paymentAdapter = new PaymentAdapter(new LegacyPaymentSystem());
        paymentAdapter.pay("USD", 5.00);
        uiLog.append("[Adapter] Legacy tip payment processed through the adapter.");

        advanceOrderState();
        lastReceiptText = receipt.buildReceiptText(currentCustomer.getName(), finalPrice)
                + "\nOrder ID: " + currentOrderId
                + "\nOrder Type: " + currentOrder.getOrderType()
                + "\nStatus: " + orderContext.getCurrentStatusText();
        uiLog.append("[Receipt] Receipt generated for " + currentCustomer.getName() + ".");
        return lastReceiptText;
    }

    public String advanceOrderState() {
        ensureOrderExists();
        String previous = orderContext.getCurrentStatusText();
        orderContext.nextState();
        String current = orderContext.getCurrentStatusText();
        if (previous.equals(current)) {
            uiLog.append("[State] Order is already in the final workflow stage.");
        } else {
            uiLog.append("[State] State changed from " + previous + " to " + current + ".");
            orderSubject.setStatus(current);
        }
        return current;
    }

    public String moveToPreviousState() {
        ensureOrderExists();
        String previous = orderContext.getCurrentStatusText();
        orderContext.previousState();
        String current = orderContext.getCurrentStatusText();
        if (previous.equals(current)) {
            uiLog.append("[State] Order remains at " + current + ".");
        } else {
            uiLog.append("[State] State changed from " + previous + " to " + current + ".");
            orderSubject.setStatus(current);
        }
        return current;
    }

    public void runAdminDemo() {
        AdminOperations adminProxy = new AdminProxy("ADMIN");
        AdminOperations customerProxy = new AdminProxy("CUSTOMER");
        adminProxy.changeRestaurantHours("08:00 AM - 10:00 PM");
        customerProxy.removeMenuItem("Burger");
        uiLog.append("[Proxy] Admin access granted for business hours update.");
        uiLog.append("[Proxy] Customer access denied for menu removal.");
    }

    public String getCurrentOrderSummary() {
        return currentOrder == null ? "No order built yet." : currentOrder.buildOrderSummary();
    }

    public String getCurrentStatus() {
        return orderContext == null ? "Not Started" : orderContext.getCurrentStatusText();
    }

    public String getCurrentOrderId() {
        return currentOrderId == null ? "N/A" : currentOrderId;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getLastReceiptText() {
        return lastReceiptText == null ? "" : lastReceiptText;
    }

    public void shutdown() {
        if (databaseConnection != null) {
            databaseConnection.disconnect();
            uiLog.append("[Singleton] Database connection closed.");
        }
    }

    private void validateCustomer(String customerName, String phoneNumber) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
    }

    private PricingStrategy resolveStrategy(String strategyName) {
        if ("Happy Hour".equalsIgnoreCase(strategyName)) {
            return new HappyHourPricing();
        }
        return new RegularPricing();
    }

    private PaymentGatewayFactory resolveGateway(String gatewayName) {
        if ("PayPal".equalsIgnoreCase(gatewayName)) {
            return new PayPalFactory();
        }
        return new StripeFactory();
    }

    private void ensureOrderExists() {
        if (currentOrder == null) {
            throw new IllegalStateException("Build an order first.");
        }
    }
}

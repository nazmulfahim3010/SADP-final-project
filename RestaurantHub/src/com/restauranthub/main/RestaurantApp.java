package com.restauranthub.main;

import com.restauranthub.gui.RestaurantFrame;
import javax.swing.SwingUtilities;

public class RestaurantApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantFrame frame = new RestaurantFrame();
            frame.setVisible(true);
        });
    }
}


















// RestaurantHub/
// ├── README.md
// └── src/
//     └── com/
//         └── restauranthub/
//             ├── main/
//             │   └── RestaurantApp.java
//             │
//             ├── controller/
//             │   ├── RestaurantController.java
//             │   └── DecoratedFoodItemAdapter.java
//             │
//             ├── gui/
//             │   ├── RestaurantFrame.java
//             │   ├── OrderPanel.java
//             │   ├── ReceiptDialog.java
//             │   ├── GuiObserver.java
//             │   └── UiLog.java
//             │
//             ├── models/
//             │   ├── Customer.java
//             │   ├── FoodMenu.java
//             │   └── Receipt.java
//             │
//             ├── creational/
//             │   ├── singleton/
//             │   │   ├── DatabaseConnection.java
//             │   │   └── RestaurantConfig.java
//             │   │
//             │   ├── factory/
//             │   │   ├── FoodItem.java
//             │   │   ├── Pizza.java
//             │   │   ├── Burger.java
//             │   │   ├── Beverage.java
//             │   │   └── FoodItemFactory.java
//             │   │
//             │   ├── abstractfactory/
//             │   │   ├── PaymentProcessor.java
//             │   │   ├── TransactionValidator.java
//             │   │   ├── ReceiptGenerator.java
//             │   │   ├── PaymentGatewayFactory.java
//             │   │   ├── StripeFactory.java
//             │   │   └── PayPalFactory.java
//             │   │
//             │   └── builder/
//             │       ├── Order.java
//             │       └── OrderBuilder.java
//             │
//             ├── structural/
//             │   ├── adapter/
//             │   │   ├── ModernPaymentInterface.java
//             │   │   ├── LegacyPaymentSystem.java
//             │   │   └── PaymentAdapter.java
//             │   │
//             │   ├── decorator/
//             │   │   ├── FoodItemComponent.java
//             │   │   ├── BaseFoodItem.java
//             │   │   ├── ToppingDecorator.java
//             │   │   ├── CheeseDecorator.java
//             │   │   └── ExtraPackagingDecorator.java
//             │   │
//             │   ├── facade/
//             │   │   ├── PaymentService.java
//             │   │   ├── InventoryService.java
//             │   │   ├── KitchenNotificationService.java
//             │   │   └── OrderProcessingFacade.java
//             │   │
//             │   └── proxy/
//             │       ├── AdminOperations.java
//             │       ├── RealAdminOperations.java
//             │       └── AdminProxy.java
//             │
//             └── behavioral/
//                 ├── observer/
//                 │   ├── Observer.java
//                 │   ├── Subject.java
//                 │   ├── OrderSubject.java
//                 │   ├── CustomerObserver.java
//                 │   └── KitchenObserver.java
//                 │
//                 ├── strategy/
//                 │   ├── PricingStrategy.java
//                 │   ├── RegularPricing.java
//                 │   ├── HappyHourPricing.java
//                 │   └── PricingContext.java
//                 │
//                 ├── command/
//                 │   ├── OrderCommand.java
//                 │   ├── PlaceOrderCommand.java
//                 │   ├── CancelOrderCommand.java
//                 │   └── OrderInvoker.java
//                 │
//                 └── state/
//                     ├── OrderState.java
//                     ├── PendingState.java
//                     ├── ConfirmedState.java
//                     ├── PreparingState.java
//                     └── OrderContext.java
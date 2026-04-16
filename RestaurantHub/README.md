# Smart Restaurant Ordering & Management System (RestaurantHub)

This is a complete, production-quality Java project demonstrating 12 essential Software Architecture and Design Patterns (SADP) in a real-world restaurant management application.

## Overview
"RestaurantHub" handles everything from customer order placement, complex food creations, various payment processes, to notifying kitchen staff and managing inventory. 

## How to Compile and Run
1. Ensure you have Java (JDK 8+) installed on your machine.
2. Navigate to the `RestaurantHub/src` folder.
3. Compile all Java files:
   ```bash
   javac com/restauranthub/main/RestaurantApp.java
   ```
4. Run the main application:
   ```bash
   java com.restauranthub.main.RestaurantApp
   ```

## Pattern Summary Table

| Pattern | Category | Use Case in Project |
| :--- | :--- | :--- |
| **Singleton** | Creational | `DatabaseConnection`, `RestaurantConfig`: Ensures only one instance handles global DB access or configurational data. |
| **Factory Method** | Creational | `FoodItemFactory`: Centralizes creation of various food items (Pizza, Burger, Beverage). |
| **Abstract Factory** | Creational | `PaymentGatewayFactory`: Creates coherent families of payment-related objects (Stripe, PayPal). |
| **Builder** | Creational | `OrderBuilder`: Allows step-by-step construction of complex combination orders. |
| **Adapter** | Structural | `PaymentAdapter`: Acts as a bridge between ModernPaymentInterface and LegacyPaymentSystem. |
| **Decorator** | Structural | `CheeseDecorator`, `ExtraPackagingDecorator`: Dynamically adds cost and description to food items. |
| **Facade** | Structural | `OrderProcessingFacade`: Simplifies interaction with Payment, Inventory, and Kitchen services. |
| **Proxy** | Structural | `AdminProxy`: Controls unauthorized execution of administrative queries. |
| **Observer** | Behavioral | `OrderSubject`, `KitchenObserver`: Notifies multiple observer components upon order state changes. |
| **Strategy** | Behavioral | `HappyHourPricing`: Allows dynamically switching calculation logic for discounts. |
| **Command** | Behavioral | `PlaceOrderCommand`: Encapsulates requests into commands for easy history/undo handling. |
| **State** | Behavioral | `OrderState` subclasses: Models distinct lifecycle parts (Pending -> Confirmed -> Preparing) natively without large switch cases. |

## Sample Output Screenshot (Text-based)
```
========================================
🍕 WELCOME TO RESTAURANTHUB SYSTEM 🍕
========================================

---> 🟢 CREATIONAL: Singleton Pattern
   [System] DatabaseConnection instance created. 🔌
   [Database] Connected to the main restaurant database. 🟢
   [System] RestaurantConfig initialized. ⚙️
   [System] Open for business? true

---> 🔵 STRUCTURAL: Proxy Pattern
   [Proxy] 🔓 Access Granted.
   [Admin] ⏰ Restaurant operating hours changed to: 08:00 AM - 10:00 PM
   [Proxy] 🚫 Access Denied: User role 'CUSTOMER' cannot remove items.
```

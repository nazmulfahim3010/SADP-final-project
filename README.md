# Hotel Management System (SADP pro)

This project is a Hotel Management System built with **Java Swing**. It focuses on clean software architecture and demonstrates the practical application of several **Software Architecture and Design Patterns (SADP)**.

## Architecture & Design Patterns

The system is built using a modular architecture where core features map to specific design patterns to ensure scalability, flexibility, and maintainability.

### 1. Creational Patterns
- **Factory Method (`RoomFactory.java`)**: 
  - Provides an interface for creating room objects but allows the factory to decide which specific room model to instantiate. 
  - **Why:** This decouples the Room Panel from the internal details of Room creation.
- **Abstract Factory / Factory (`UIFactory.java`)**: 
  - Centralizes the creation and styling of UI components.
  - **Why:** Decouples the UI implementation details (colors, animations, shapes) from the functional panels (CustomerPanel, etc.). This ensures visual consistency across the entire application and allows changing the theme in one centralized place.
- **Singleton (`DataStore.java`)**: 
  - Ensures that there is only one globally accessible database instance throughout the application's lifecycle.
  - **Why:** It avoids inconsistencies and multiple conflicting states of data, serving as the central repository/database.

### 2. Structural Patterns
- **Facade (`ReservationFacade.java`)**: 
  - Provides a simplified, high-level interface to the complex room booking workflow involving various subsystems.
  - **Why:** Instead of the GUI panels dealing with the DataStore, Room status updates, and external Payment Adapters individually, they simply call a single unified method (`bookRoom`) on the Facade. This hides system complexity.
- **Adapter (`BkashAdapter.java`, `ExternalBkashAPI.java`)**: 
  - A wrapper class acting as a translator between our system's `PaymentProcessor` interface and a third-party API.
  - **Why:** Converts the specific interface of `ExternalBkashAPI` into the generic `PaymentProcessor` interface required by our `ReservationFacade`, promoting integration of external systems without modifying the internal code.

### 3. Behavioral Patterns
- **Observer (`DataObserver.java`, `DataStore.java`)**: 
  - Provides a common contract (`onDataChanged`) and acts as a Subject (`DataStore`) maintaining a list of data observers.
  - **Why:** Notifies all GUI observers automatically when data changes (e.g., a new room or customer is added). This ensures the UI stays perfectly synchronized with the core model without tight coupling between them.
- **Strategy (`PricingStrategy.java`, `StandardPricing.java`, `OffSeasonDiscount.java`)**: 
  - An interface that defines a family of algorithms for calculating room prices.
  - **Why:** Makes price calculation algorithms interchangeable. The system can easily add new pricing rules (e.g., seasonal, holiday, corporate) without modifying the Reservation's core logic.
- **Memento (`DataStoreMemento.java`)**: 
  - Exists to store historical snapshots of the data (like the customers list).
  - **Why:** Captures and externalizes an object's internal state so that it can be restored later without violating encapsulation. In this project, it enables a rollback/'Undo' functionality for customer management.

## How to Run
To compile and run the project from the src directory:

**1. Compile:**
```bash
javac -d bin src/com/hotel/**/*.java
```

**2. Run:**
```bash
java -cp bin com.hotel.Main
```

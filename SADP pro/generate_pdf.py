import os
from fpdf import FPDF

class PDF(FPDF):
    def header(self):
        self.set_font('Arial', 'B', 15)
        self.cell(0, 10, 'Hotel Management System (SADP pro) - Design Patterns Document', 0, 1, 'C')
        self.ln(5)

    def chapter_title(self, num, label):
        self.set_font('Arial', 'B', 12)
        self.set_fill_color(200, 220, 255)
        self.cell(0, 8, f'{num}. {label}', 0, 1, 'L', 1)
        self.ln(4)

    def chapter_body(self, intro, why):
        self.set_font('Arial', '', 11)
        self.multi_cell(0, 6, intro)
        self.ln(2)
        self.set_font('Arial', 'B', 11)
        self.cell(0, 6, 'Why it was used:')
        self.ln(6)
        self.set_font('Arial', '', 11)
        self.multi_cell(0, 6, why)
        self.ln(8)

patterns = [
    {
        "name": "Factory Pattern (RoomFactory.java) & Abstract Factory Pattern (UIFactory.java)",
        "intro": "- RoomFactory.java: Provides an interface for creating room objects but allows the factory to decide which specific room model to instantiate.\n- UIFactory.java: Centralizes the creation and styling of UI components.",
        "why": "RoomFactory decouples the Room Panel from the internal details of Room creation. UIFactory decouples the UI implementation details (colors, animations, shapes) from the functional panels (CustomerPanel, etc.). This ensures visual consistency across the entire application and allows changing the theme in one centralized place."
    },
    {
        "name": "Singleton Pattern (DataStore.java)",
        "intro": "- DataStore.java: Serves as the central repository/database for the hotel management application.",
        "why": "Ensures that there is only one globally accessible instance of the database (DataStore) throughout the application's lifecycle. It avoids inconsistencies and multiple conflicting states of data."
    },
    {
        "name": "Observer Pattern (DataObserver.java, DataStore.java)",
        "intro": "- DataObserver.java: Provides a common contract (`onDataChanged`) for any component that needs to react to changes in the DataStore.\n- DataStore.java: Acts as the Subject, maintaining a list of data observers.",
        "why": "It notifies all graphical user interface observers automatically when data changes (e.g., a new room or customer is added). This ensures the UI stays in perfect synchronization with the core model without tight coupling between them."
    },
    {
        "name": "Facade Pattern (ReservationFacade.java)",
        "intro": "- ReservationFacade.java: Provides a simplified, high-level interface to the complex room booking workflow involving various subsystems.",
        "why": "Instead of the UI panels having to deal with the DataStore, Room status updates, and external Payment Adapters individually, they simply call a single unified method (`bookRoom`) on the Facade. This hides system complexity and makes the subsystems easier to use."
    },
    {
        "name": "Strategy Pattern (PricingStrategy.java, StandardPricing.java, OffSeasonDiscount.java)",
        "intro": "- PricingStrategy.java: An interface that defines a family of algorithms for calculating room prices.",
        "why": "It makes price calculation algorithms interchangeable. The system can easily add new pricing rules (e.g., seasonal, holiday, corporate) by implementing the Strategy interface without modifying the Reservation's core logic."
    },
    {
        "name": "Adapter Pattern (BkashAdapter.java, ExternalBkashAPI.java)",
        "intro": "- BkashAdapter.java: A wrapper class acting as a translator between our system's `PaymentProcessor` interface and the third-party `ExternalBkashAPI`.",
        "why": "It converts the specific, possibly incompatible interface of `ExternalBkashAPI` into the generic `PaymentProcessor` interface required by our system (`ReservationFacade`), promoting integration of external systems without modifying internal code."
    },
    {
        "name": "Memento Pattern (DataStoreMemento.java)",
        "intro": "- DataStoreMemento.java: Exists to store historical snapshots of the data (e.g., customers list).",
        "why": "It captures and externalizes an object's internal state so that it can be restored later without violating encapsulation. In our project, it enables an 'Undo' functionality for customer management (rolling back customer deletion or addition)."
    }
]

pdf = PDF()
pdf.add_page()

for i, p in enumerate(patterns):
    pdf.chapter_title(i + 1, p['name'])
    pdf.chapter_body(p['intro'], p['why'])

out_path = os.path.join("c:\\Users\\Acer\\OneDrive\\Desktop\\SADP_TEST\\SADP pro", "Design_Patterns_Report.pdf")
pdf.output(out_path)
print(f"PDF generated successfully at {out_path}")

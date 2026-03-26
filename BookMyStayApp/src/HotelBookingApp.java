/**
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates object modeling through inheritance
 * and abstraction. Room types are defined with static
 * availability stored as individual variables.
 *
 * @author Developer
 * @version 2.0
 */
public class HotelBookingApp {

    /**
     * Abstract base class representing a hotel room.
     */
    abstract static class Room {
        protected int beds;
        protected int size;
        protected double pricePerNight;

        public Room(int beds, int size, double pricePerNight) {
            this.beds = beds;
            this.size = size;
            this.pricePerNight = pricePerNight;
        }

        public abstract String getRoomType();

        public void displayDetails(int availability) {
            System.out.println(getRoomType() + ":");
            System.out.println("Beds: " + beds);
            System.out.println("Size: " + size + " sqft");
            System.out.println("Price per night: " + pricePerNight);
            System.out.println("Available: " + availability);
            System.out.println();
        }
    }

    /**
     * Concrete class for Single Room.
     */
    static class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 200, 1500.0);
        }

        @Override
        public String getRoomType() {
            return "Single Room";
        }
    }

    /**
     * Concrete class for Double Room.
     */
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 2500.0);
        }

        @Override
        public String getRoomType() {
            return "Double Room";
        }
    }

    /**
     * Concrete class for Suite Room.
     */
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super(3, 750, 5000.0);
        }

        @Override
        public String getRoomType() {
            return "Suite Room";
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleBed = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        single.displayDetails(singleAvailable);
        doubleBed.displayDetails(doubleAvailable);
        suite.displayDetails(suiteAvailable);
    }
}
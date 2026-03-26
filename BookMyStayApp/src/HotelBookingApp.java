import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * Enables read-only access to room availability. Guests can
 * search and view available rooms without modifying inventory.
 * Demonstrates separation of read and write operations.
 *
 * @author Developer
 * @version 4.0
 */
public class HotelBookingApp {

    /**
     * Room inventory manager.
     */
    static class RoomInventory {
        private Map<String, Integer> inventory;
        private Map<String, Double> prices;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 0);

            prices = new HashMap<>();
            prices.put("Single", 1500.0);
            prices.put("Double", 2500.0);
            prices.put("Suite", 5000.0);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public Map<String, Integer> getInventory() {
            return inventory;
        }

        public double getPrice(String roomType) {
            return prices.getOrDefault(roomType, 0.0);
        }
    }

    /**
     * Search service for read-only room access.
     */
    static class RoomSearchService {
        private RoomInventory inventory;

        public RoomSearchService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        public void searchAvailableRooms() {
            System.out.println("Available Rooms:");
            for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.println(entry.getKey() + " Room - Available: "
                            + entry.getValue()
                            + ", Price: Rs." + inventory.getPrice(entry.getKey()) + "/night");
                }
            }
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.searchAvailableRooms();
    }
}
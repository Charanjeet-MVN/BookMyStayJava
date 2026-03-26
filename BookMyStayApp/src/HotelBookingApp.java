import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Replaces scattered availability variables with a centralized
 * HashMap to manage room inventory. Demonstrates single source
 * of truth using HashMap<String, Integer>.
 *
 * @author Developer
 * @version 3.0
 */
public class HotelBookingApp {

    /**
     * Centralized room inventory manager using HashMap.
     */
    static class RoomInventory {
        private Map<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }

        public void displayInventory() {
            System.out.println("Current Room Inventory:");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
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
        inventory.displayInventory();

        System.out.println("\nUpdating Single room availability to 4...");
        inventory.updateAvailability("Single", 4);

        System.out.println("\nUpdated Room Inventory:");
        inventory.displayInventory();
    }
}
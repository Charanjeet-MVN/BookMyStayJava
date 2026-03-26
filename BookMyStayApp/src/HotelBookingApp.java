import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * Confirms booking requests by assigning unique room IDs.
 * Uses Set to enforce uniqueness and prevent double-booking.
 * Inventory is updated immediately after each allocation.
 *
 * @author Developer
 * @version 6.0
 */
public class HotelBookingApp {

    /**
     * Represents a guest's booking request.
     */
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }

        @Override
        public String toString() {
            return "Reservation[Guest=" + guestName + ", RoomType=" + roomType + "]";
        }
    }

    /**
     * Room inventory manager using HashMap.
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

        public void decrementAvailability(String roomType) {
            int current = inventory.getOrDefault(roomType, 0);
            if (current > 0) {
                inventory.put(roomType, current - 1);
            }
        }
    }

    /**
     * Room allocation service using Set for unique room IDs.
     */
    static class BookingAllocationService {
        private Queue<Reservation> requestQueue;
        private RoomInventory inventory;
        private Set<String> allocatedRoomIds;
        private Map<String, Set<String>> roomTypeAllocations;
        private int roomCounter;

        public BookingAllocationService(RoomInventory inventory) {
            this.requestQueue = new LinkedList<>();
            this.inventory = inventory;
            this.allocatedRoomIds = new HashSet<>();
            this.roomTypeAllocations = new HashMap<>();
            this.roomCounter = 1000;
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        public void processAllRequests() {
            while (!requestQueue.isEmpty()) {
                Reservation reservation = requestQueue.poll();
                allocateRoom(reservation);
            }
        }

        private void allocateRoom(Reservation reservation) {
            String roomType = reservation.getRoomType();
            if (inventory.getAvailability(roomType) > 0) {
                String roomId = roomType.substring(0, 1) + "-" + (++roomCounter);
                if (!allocatedRoomIds.contains(roomId)) {
                    allocatedRoomIds.add(roomId);
                    roomTypeAllocations.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);
                    inventory.decrementAvailability(roomType);
                    System.out.println("Confirmed: " + reservation.getGuestName()
                            + " assigned " + roomType + " Room ID: " + roomId);
                }
            } else {
                System.out.println("Failed: No " + roomType + " rooms available for "
                        + reservation.getGuestName());
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
        BookingAllocationService service = new BookingAllocationService(inventory);

        service.addRequest(new Reservation("Alice", "Single"));
        service.addRequest(new Reservation("Bob", "Double"));
        service.addRequest(new Reservation("Charlie", "Suite"));
        service.addRequest(new Reservation("Diana", "Single"));

        service.processAllRequests();
    }
}
import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * Handles multiple booking requests fairly using a Queue
 * data structure. Requests are stored and processed in
 * FIFO order. No inventory modification at this stage.
 *
 * @author Developer
 * @version 5.0
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

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        @Override
        public String toString() {
            return "Reservation[Guest=" + guestName + ", RoomType=" + roomType + "]";
        }
    }

    /**
     * Queue-based booking request manager.
     */
    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;

        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
            System.out.println("Request added: " + reservation);
        }

        public Reservation processNextRequest() {
            return requestQueue.poll();
        }

        public int getPendingCount() {
            return requestQueue.size();
        }

        public void displayQueue() {
            System.out.println("Pending Booking Requests: " + requestQueue.size());
            for (Reservation r : requestQueue) {
                System.out.println(" - " + r);
            }
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Double"));
        queue.addRequest(new Reservation("Charlie", "Suite"));

        System.out.println();
        queue.displayQueue();
    }
}
import java.util.*;

// UC7: Add-On Service Selection
// Goal: Extend the booking model to support optional services

class HotelBookingApp {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return serviceName + " ($" + cost + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added service: " + service.getServiceName() + " to reservation " + reservationId);
    }

    public List<AddOnService> getServices(String reservationId) {
        return reservationServices.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateTotalAddOnCost(String reservationId) {
        return getServices(reservationId).stream().mapToDouble(AddOnService::getCost).sum();
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);
        if (services.isEmpty()) {
            System.out.println("No add-on services for reservation: " + reservationId);
        } else {
            System.out.println("Add-on services for reservation " + reservationId + ":");
            for (AddOnService s : services) {
                System.out.println("  - " + s);
            }
            System.out.println("  Total add-on cost: $" + calculateTotalAddOnCost(reservationId));
        }
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("=== UC7: Add-On Service Selection ===");

        AddOnServiceManager manager = new AddOnServiceManager();

        AddOnService breakfast = new AddOnService("Breakfast", 15.00);
        AddOnService spa = new AddOnService("Spa Access", 50.00);
        AddOnService parking = new AddOnService("Parking", 10.00);

        String reservationId = "RES-001";
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, parking);

        manager.displayServices(reservationId);

        String reservationId2 = "RES-002";
        manager.addService(reservationId2, new AddOnService("Airport Pickup", 25.00));
        manager.displayServices(reservationId2);

        System.out.println("\nBooking and inventory state remain unchanged.");
    }
}
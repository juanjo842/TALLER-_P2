package entities;

public class NationalRoute extends Route {
    private double durationHours;
    private boolean includesStops;

    public NationalRoute(String routeCode, String originCity, String destinationCity,
                         String departureDate, String departureTime, String arrivalTime,
                         int totalCapacity, double basePrice,
                         double durationHours, boolean includesStops) {

        super(routeCode, originCity, destinationCity, departureDate,
                departureTime, arrivalTime, totalCapacity, basePrice);

        this.durationHours = durationHours;
        this.includesStops = includesStops;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice(); // Sin cargos adicionales
    }

    public double getDurationHours() {
        return durationHours;
    }

    public boolean isIncludesStops() {
        return includesStops;
    }

    @Override
    public String toString() {
        return "NACIONAL | " + super.toString() +
                String.format(
                        " | Duración: %.1fh | Paradas: %s",
                        durationHours,
                        includesStops ? "Sí" : "No"
                );
    }
}
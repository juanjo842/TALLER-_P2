package entities;

public class InternationalRoute extends Route {
    private String destinationCountry;
    private boolean passportRequired;
    private double internationalFee;

    public InternationalRoute(String routeCode, String originCity, String destinationCity,
                              String departureDate, String departureTime, String arrivalTime,
                              int totalCapacity, double basePrice,
                              String destinationCountry, boolean passportRequired,
                              double internationalFee) {

        super(routeCode, originCity, destinationCity, departureDate,
                departureTime, arrivalTime, totalCapacity, basePrice);

        this.destinationCountry = destinationCountry;
        this.passportRequired = passportRequired;
        this.internationalFee = internationalFee;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() + internationalFee;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public boolean isPassportRequired() {
        return passportRequired;
    }

    public double getInternationalFee() {
        return internationalFee;
    }

    @Override
    public String toString() {
        return "INTERNACIONAL | " + super.toString() +
                String.format(
                        " | País: %s | Pasaporte: %s | Cargo intl: $%.0f",
                        destinationCountry,
                        passportRequired ? "Sí" : "No",
                        internationalFee
                );
    }
}
package service;

import entities.Route;
import entities.NationalRoute;
import entities.InternationalRoute;
import repository.RouteRepository;
import java.util.ArrayList;

public class RouteService {
    private RouteRepository repository;

    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }

    public void createNationalRoute(String code, String origin, String destination,
                                    String date, String departureTime,
                                    String arrivalTime, int capacity,
                                    double basePrice, double durationHours,
                                    boolean includesStops) {

        try {
            validateRoute(code, capacity, basePrice);

            NationalRoute route = new NationalRoute(
                    code, origin, destination, date,
                    departureTime, arrivalTime,
                    capacity, basePrice,
                    durationHours, includesStops
            );

            repository.add(route);

            System.out.println(
                    " Ruta nacional creada: "
            );

        } catch (Exception e) {
            System.out.println(
                    " Error al crear ruta nacional: "
                            + e.getMessage());
        }
    }

    public void createInternationalRoute(String code, String origin,
                                         String destination, String date,
                                         String departureTime,
                                         String arrivalTime, int capacity,
                                         double basePrice,
                                         String destinationCountry,
                                         boolean passportRequired,
                                         double internationalFee) {

        try {
            validateRoute(code, capacity, basePrice);

            InternationalRoute route = new InternationalRoute(
                    code, origin, destination, date,
                    departureTime, arrivalTime,
                    capacity, basePrice,
                    destinationCountry,
                    passportRequired,
                    internationalFee
            );

            repository.add(route);

            System.out.println(
                    " Ruta internacional creada: ");

        } catch (Exception e) {
            System.out.println(
                    " Error al crear ruta internacional: "
                            + e.getMessage()
            );
        }
    }

    private void validateRoute(String code, int capacity,
                               double basePrice) throws Exception {

        if (repository.findByCode(code) != null) {
            throw new Exception("Ya existe una ruta con ese código");
        }

        if (capacity <= 0) {
            throw new Exception(
                    "La capacidad total debe ser mayor a 0"
            );
        }

        if (capacity > 32) {
            throw new Exception(
                    "La capacidad máxima del bus es 32 puestos"
            );
        }

        if (basePrice <= 0) {
            throw new Exception(
                    "El precio de la ruta debe ser mayor a cero"
            );
        }
    }

    public Route findByCode(String code) {
        return repository.findByCode(code);
    }

    public ArrayList<Route> listAll() {
        return repository.listAll();
    }
}
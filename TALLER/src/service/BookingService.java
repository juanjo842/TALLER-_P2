package service;

import entities.Passenger;
import entities.Booking;
import entities.Route;
import repository.PassengerRepository;
import repository.BookingRepository;
import repository.RouteRepository;
import java.util.ArrayList;

public class BookingService {
    private BookingRepository bookingRepository;
    private PassengerRepository passengerRepository;
    private RouteRepository routeRepository;

    public BookingService(BookingRepository bookingRepository,
                          PassengerRepository passengerRepository,
                          RouteRepository routeRepository) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.routeRepository = routeRepository;
    }

    public void createBooking(String bookingCode, String id, String routeCode,
                              int seatQuantity, String bookingDate) {

        try {
            if (bookingRepository.findByCode(bookingCode) != null) {
                throw new Exception("Ya existe una reserva con ese código");
            }

            Passenger passenger = passengerRepository.findById(id);

            if (passenger == null) {
                throw new Exception("No se encontró el pasajero con esa cédula");
            }

            Route route = routeRepository.findByCode(routeCode);

            if (route == null) {
                throw new Exception("No se encontró la ruta con ese código");
            }

            if (!route.getStatus().equals("Programada")) {
                throw new Exception("La ruta no está en estado Programada");
            }

            if (seatQuantity < 1 || seatQuantity > 5) {
                throw new Exception("No se pueden reservar más de 5 puestos por reserva");
            }

            if (route.getAvailableSeats() < seatQuantity) {
                throw new Exception("No hay puestos disponibles para esta ruta");
            }

            Booking booking = new Booking(
                    bookingCode,
                    passenger,
                    route,
                    seatQuantity,
                    bookingDate
            );

            route.setAvailableSeats(
                    route.getAvailableSeats() - seatQuantity
            );

            bookingRepository.add(booking);

            System.out.println("✔ Reserva creada exitosamente:");
            System.out.println("   Código: " + bookingCode);
            System.out.println("   Pasajero: "
                    + passenger.getFirstName() + " "
                    + passenger.getLastName());

            System.out.println("   Ruta: "
                    + route.getOriginCity() + " -> "
                    + route.getDestinationCity());

            System.out.println("   Puestos: " + seatQuantity);

            System.out.printf(
                    "   Precio total: $%.0f%n",
                    booking.getTotalPrice()
            );

            System.out.println(
                    "   Puestos disponibles restantes: "
                            + route.getAvailableSeats()
            );

        } catch (Exception e) {
            System.out.println("✘ Error al crear reserva: " + e.getMessage());
        }
    }

    public void cancelBooking(String bookingCode) {

        try {
            Booking booking = bookingRepository.findByCode(bookingCode);

            if (booking == null) {
                throw new Exception("No se encontró la reserva con ese código");
            }

            if (booking.getStatus().equals("Cancelada")) {
                throw new Exception("La reserva ya está cancelada");
            }

            int returnedSeats = booking.getSeatQuantity();

            Route route = booking.getRoute();

            route.setAvailableSeats(
                    route.getAvailableSeats() + returnedSeats
            );

            booking.setStatus("Cancelada");

            System.out.println("✔ Reserva " + bookingCode + " cancelada.");

            System.out.println(
                    "   Puestos devueltos a ruta "
                            + route.getRouteCode() + ": "
                            + returnedSeats
            );

            System.out.println(
                    "   Puestos disponibles: "
                            + route.getAvailableSeats()
            );

        } catch (Exception e) {
            System.out.println("✘ Error al cancelar reserva: " + e.getMessage());
        }
    }

    public void consultBooking(String bookingCode) {

        try {
            Booking booking = bookingRepository.findByCode(bookingCode);

            if (booking == null) {
                throw new Exception("No se encontró la reserva con ese código");
            }

            System.out.println(booking);

        } catch (Exception e) {
            System.out.println("✘ " + e.getMessage());
        }
    }

    public void bookingsByPassenger(String id) {

        try {
            Passenger passenger = passengerRepository.findById(id);

            if (passenger == null) {
                throw new Exception("No se encontró el pasajero con esa cédula");
            }

            ArrayList<Booking> list = bookingRepository.findById(id);

            System.out.println(
                    "Reservas de "
                            + passenger.getFirstName() + " "
                            + passenger.getLastName() + ":"
            );

            if (list.isEmpty()) {
                System.out.println("   (Sin reservas registradas)");
            } else {
                for (Booking b : list) {
                    System.out.println("   " + b);
                }
            }

        } catch (Exception e) {
            System.out.println("✘ " + e.getMessage());
        }
    }
}
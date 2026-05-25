import repository.*;
import service.*;
import entities.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PassengerRepository passengerRepo = new PassengerRepository();
        RouteRepository routeRepo = new RouteRepository();
        BookingRepository bookingRepo = new BookingRepository();

        PassengerService passengerService = new PassengerService(passengerRepo);
        RouteService routeService = new RouteService(routeRepo);
        BookingService bookingService = new BookingService(bookingRepo, passengerRepo, routeRepo);

        int op;
        do {

            System.out.println("    FLUXIOBUS COLOMBIA - SISTEMA     ");
            System.out.println("  1. Registrar pasajero               ");
            System.out.println("  2. Crear ruta nacional              ");
            System.out.println("  3. Crear ruta internacional         ");
            System.out.println(" 4. Realizar reserva                 ");
            System.out.println(" 5. Cancelar reserva                 ");
            System.out.println(" 6. Consultar reserva por código     ");
            System.out.println("  7. Reservas por pasajero (cédula)   ");
            System.out.println(" 8. Total de pasajeros registrados   ");
            System.out.println(" 9. Listar todas las rutas           ");
            System.out.println(" 0. Salir                            ");

            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) menuRegisterPassenger(sc, passengerService);
            if (op == 2) menuCreateNationalRoute(sc, routeService);
            if (op == 3) menuCreateInternationalRoute(sc, routeService);
            if (op == 4) menuCreateBooking(sc, bookingService);
            if (op == 5) menuCancelBooking(sc, bookingService);
            if (op == 6) menuConsultBooking(sc, bookingService);
            if (op == 7) menuBookingsByPassenger(sc, bookingService);
            if (op == 8) System.out.println("Total: " + passengerRepo.totalPassengers());
            if (op == 9) listRoutes(sc, routeService);

        } while (op != 0);

        sc.close(); // <-- buena práctica cerrarlo al salir
    }

    static void menuRegisterPassenger(Scanner sc, PassengerService passengerService) {
        System.out.println("\n--- REGISTRAR PASAJERO ---");
        System.out.print("Cédula: ");
        String id = sc.nextLine();
        System.out.print("Nombres: ");
        String firstName = sc.nextLine();
        System.out.print("Apellidos: ");
        String lastName = sc.nextLine();
        System.out.print("Edad: ");
        int age = 0;
        try {
            age = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Edad inválida.");
            return;
        }
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Teléfono: ");
        String phone = sc.nextLine();
        System.out.print("Número de pasaporte: ");
        String passport = sc.nextLine();
        System.out.print("Nacionalidad: ");
        String nationality = sc.nextLine();
        passengerService.registerPassenger(id, firstName, lastName, age, email, phone, passport, nationality);
    }

    static void menuCreateNationalRoute(Scanner sc, RouteService routeService) {
        System.out.println("\n--- CREAR RUTA NACIONAL ---");
        System.out.print("Código de ruta: ");
        String code = sc.nextLine();
        System.out.print("Ciudad origen: ");
        String origin = sc.nextLine();
        System.out.print("Ciudad destino: ");
        String destination = sc.nextLine();
        System.out.print("Fecha (DD/MM/YYYY): ");
        String date = sc.nextLine();
        System.out.print("Hora salida (HH:MM): ");
        String departureTime = sc.nextLine();
        System.out.print("Hora llegada (HH:MM): ");
        String arrivalTime = sc.nextLine();
        System.out.print("Capacidad (max 32): ");
        try {
            int capacity = Integer.parseInt(sc.nextLine());
            System.out.print("Precio base: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Duración en horas: ");
            double duration = Double.parseDouble(sc.nextLine());
            System.out.print("¿Incluye paradas? (s/n): ");
            boolean stops = sc.nextLine().equalsIgnoreCase("s");
            routeService.createNationalRoute(code, origin, destination, date, departureTime, arrivalTime, capacity, price, duration, stops);
        } catch (NumberFormatException e) {
            System.out.println("✘ Valor numérico inválido.");
        }
    }

    static void menuCreateInternationalRoute(Scanner sc, RouteService routeService) {
        System.out.println("\n--- CREAR RUTA INTERNACIONAL ---");
        System.out.print("Código de ruta: ");
        String code = sc.nextLine();
        System.out.print("Ciudad origen: ");
        String origin = sc.nextLine();
        System.out.print("Ciudad destino: ");
        String destination = sc.nextLine();
        System.out.print("Fecha (DD/MM/YYYY): ");
        String date = sc.nextLine();
        System.out.print("Hora salida (HH:MM): ");
        String departureTime = sc.nextLine();
        System.out.print("Hora llegada (HH:MM): ");
        String arrivalTime = sc.nextLine();
        System.out.print("Capacidad (max 32): ");
        try {
            int capacity = Integer.parseInt(sc.nextLine());
            System.out.print("Precio base: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("País destino: ");
            String country = sc.nextLine();
            System.out.print("¿Requiere pasaporte? (s/n): ");
            boolean passportRequired = sc.nextLine().equalsIgnoreCase("s");
            System.out.print("Cargo internacional: ");
            double fee = Double.parseDouble(sc.nextLine());
            routeService.createInternationalRoute(code, origin, destination, date, departureTime, arrivalTime, capacity, price, country, passportRequired, fee);
        } catch (NumberFormatException e) {
            System.out.println("✘ Valor numérico inválido.");
        }
    }

    static void menuCreateBooking(Scanner sc, BookingService bookingService) {
        System.out.println("\n--- REALIZAR RESERVA ---");
        System.out.print("Código de reserva (ej: R001): ");
        String code = sc.nextLine();
        System.out.print("Cédula del pasajero: ");
        String id = sc.nextLine();
        System.out.print("Código de ruta: ");
        String routeCode = sc.nextLine();
        System.out.print("Cantidad de puestos (1-5): ");
        try {
            int seats = Integer.parseInt(sc.nextLine());
            System.out.print("Fecha de reserva (DD/MM/YYYY): ");
            String date = sc.nextLine();
            bookingService.createBooking(code, id, routeCode, seats, date);
        } catch (NumberFormatException e) {
            System.out.println("✘ Cantidad inválida.");
        }
    }

    static void menuCancelBooking(Scanner sc, BookingService bookingService) {
        System.out.println("\n--- CANCELAR RESERVA ---");
        System.out.print("Código de reserva: ");
        String code = sc.nextLine();
        bookingService.cancelBooking(code);
    }

    static void menuConsultBooking(Scanner sc, BookingService bookingService) {
        System.out.println("\n--- CONSULTAR RESERVA ---");
        System.out.print("Código de reserva: ");
        String code = sc.nextLine();
        bookingService.consultBooking(code);
    }

    static void menuBookingsByPassenger(Scanner sc, BookingService bookingService) {
        System.out.println("\n--- RESERVAS POR PASAJERO ---");
        System.out.print("Cédula del pasajero: ");
        String id = sc.nextLine();
        bookingService.bookingsByPassenger(id);
    }

    static void listRoutes(Scanner sc, RouteService routeService) {
        ArrayList<Route> routes = routeService.listAll();
        if (routes.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }
        System.out.println("\n--- RUTAS DISPONIBLES ---");
        for (Route r : routes) {
            System.out.println(r);
        }
    }
}
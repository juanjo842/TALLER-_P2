package entities;

public class Booking {


        private String reservationCode;
        private Passenger passenger;
        private Route route;
        private int seatQuantity;
        private String reservationDate;
        private double totalPrice;
        private String status; // "Confirmada", "Cancelada", "Completada"

        public Booking(String reservationCode, Passenger passenger, Route route,
                           int seatQuantity, String reservationDate) {
            this.reservationCode = reservationCode;
            this.passenger = passenger;
            this.route = route;
            this.seatQuantity = seatQuantity;
            this.reservationDate = reservationDate;
            this.totalPrice = route.calculateFinalPrice() * seatQuantity;
            this.status = "Confirmada";
        }

        public String getReservationCode() { return reservationCode; }
        public Passenger getPassenger() { return passenger; }
        public Route getRoute() { return route; }
        public int getSeatQuantity() { return seatQuantity; }
        public String getReservationDate() { return reservationDate; }
        public double getTotalPrice() { return totalPrice; }
        public String getStatus() { return status; }

        public void setStatus(String status) { this.status = status; }

        @Override
        public String toString() {
            return String.format(
                    "Reserva[Código: %s | Pasajero: %s %s (Cédula: %s) | Ruta: %s -> %s (%s) | Puestos: %d | Precio total: $%.0f | Fecha: %s | Estado: %s]",
                    reservationCode,
                    passenger.getFirstName(), passenger.getLastName(), passenger.getId(),
                    route.getOriginCity(), route.getDestinationCity(), route.getRouteCode(),
                    seatQuantity, totalPrice, reservationDate, status
            );
        }
    }




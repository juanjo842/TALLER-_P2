package entities;

public abstract class Route {



        private String routeCode;
        private String originCity;
        private String destinationCity;
        private String departureDate;
        private String departureTime;
        private String arrivalTime;
        private int totalCapacity;
        private int availableSeats;
        private double basePrice;
        private String status; // "Programada", "En Camino", "Completada", "Cancelada"

        public Route(String routeCode, String originCity, String destinationCity,
                     String departureDate, String departureTime, String arrivalTime,
                     int totalCapacity, double basePrice) {
            this.routeCode = routeCode;
            this.originCity = originCity;
            this.destinationCity = destinationCity;
            this.departureDate = departureDate;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.totalCapacity = totalCapacity;
            this.availableSeats = totalCapacity;
            this.basePrice = basePrice;
            this.status = "Programada";
        }

        public abstract double calculateFinalPrice();

        public String getRouteCode() { return routeCode; }
        public String getOriginCity() { return originCity; }
        public String getDestinationCity() { return destinationCity; }
        public String getDepartureDate() { return departureDate; }
        public String getDepartureTime() { return departureTime; }
        public String getArrivalTime() { return arrivalTime; }
        public int getTotalCapacity() { return totalCapacity; }
        public int getAvailableSeats() { return availableSeats; }
        public double getBasePrice() { return basePrice; }
        public String getStatus() { return status; }

        public void setAvailableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return String.format(
                    "[%s] %s -> %s | Fecha: %s | Salida: %s | Llegada: %s | Puestos: %d/%d | Precio final: $%.0f | Estado: %s",
                    routeCode, originCity, destinationCity, departureDate,
                    departureTime, arrivalTime, availableSeats, totalCapacity,
                    calculateFinalPrice(), status
            );
        }
    }


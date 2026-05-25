package service;

import entities.Passenger;
import repository.PassengerRepository;
import java.util.ArrayList;

public class PassengerService {
    private PassengerRepository repository;

    public PassengerService(PassengerRepository repository) {
        this.repository = repository;
    }

    public void registerPassenger(String id, String firstName, String lastName, int age,
                                  String email, String phone, String passport, String nationality) {
        try {

            if (age < 0) {
                throw new Exception("La edad no puede ser negativa");
            }

            if (!email.contains("@")) {
                throw new Exception("El email debe contener el símbolo @");
            }

            if (repository.findById(id) != null) {
                throw new Exception("Ya existe un pasajero con esa cédula");
            }

            if (repository.findByPassport(passport) != null) {
                throw new Exception("Ya existe un pasajero con ese número de pasaporte");
            }

            Passenger passenger = new Passenger(
                    id, firstName, lastName, age,
                    email, phone, passport, nationality
            );

            repository.add(passenger);

            System.out.println(" Pasajero registrado exitosamente: "
                    + firstName + " " + lastName);

        } catch (Exception e) {
            System.out.println(" Error al registrar pasajero: " + e.getMessage());
        }
    }

    public Passenger findById(String id) {
        return repository.findById(id);
    }

    public int totalPassengers() {
        return repository.totalPassengers();
    }

    public ArrayList<Passenger> listAll() {
        return repository.listAll();
    }
}
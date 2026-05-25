package repository;

import entities.Passenger;
import java.util.ArrayList;

public class PassengerRepository {
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    public Passenger findById(String id) {
        for (Passenger p : passengers) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Passenger findByPassport(String passport) {
        for (Passenger p : passengers) {
            if (p.getPassportNumber().equals(passport)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Passenger> listAll() {
        return passengers;
    }

    public int totalPassengers() {
        return passengers.size();
    }
}
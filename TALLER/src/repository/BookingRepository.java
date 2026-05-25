package repository;

import entities.Booking;
import java.util.ArrayList;

public class BookingRepository {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void add(Booking booking) {
        bookings.add(booking);
    }

    public Booking findByCode(String code) {
        for (Booking b : bookings) {
            if (b.getReservationCode().equals(code)) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Booking> findById(String id) {
        ArrayList<Booking> result = new ArrayList<>();

        for (Booking b : bookings) {
            if (b.getPassenger().getId().equals(id)) {
                result.add(b);
            }
        }

        return result;
    }

    public ArrayList<Booking> listAll() {
        return bookings;
    }
}
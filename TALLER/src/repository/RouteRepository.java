package repository;

import entities.Route;
import java.util.ArrayList;

public class RouteRepository {
    private ArrayList<Route> routes = new ArrayList<>();

    public void add(Route route) {
        routes.add(route);
    }

    public Route findByCode(String code) {
        for (Route r : routes) {
            if (r.getRouteCode().equals(code)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Route> listAll() {
        return routes;
    }
}
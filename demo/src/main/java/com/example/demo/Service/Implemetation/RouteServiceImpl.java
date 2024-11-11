package com.example.demo.Service.Implemetation;

import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.Shipment;
import com.example.demo.Repository.RouteRepository;
import com.example.demo.Service.GeoCodingService;
import com.example.demo.Service.LocationService;
import com.example.demo.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final LocationService locationService;
    private final GeoCodingService geoCodingService;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository,
                            LocationService locationService,
                            GeoCodingService geoCodingService) {
        this.routeRepository = routeRepository;
        this.locationService = locationService;
        this.geoCodingService = geoCodingService;
    }

    public Route calculateOptimalRoute(Shipment shipment) {

        // 1. Geocode addresses to get coordinates
        Location originLocation = geocodeAddress(shipment.getOrigin());
        Location destinationLocation = geocodeAddress(shipment.getDestination());

        // 2. Retrieve all relevant locations from the database
        List<Location> allLocations = locationService.getAllLocations();

        // 3. Calculate distances between all locations
        Map<Location, Map<Location,Double>> distanceMatrix = calculateDistanceMatrix(allLocations);

        // 4. Apply a route optimization algorithm (Dijkstra's algorithm)
        List<Location> optimalRoute = findShortestPath(originLocation,destinationLocation,distanceMatrix);

        // 5. Create a Route object with the optimized route
        Route route = new Route();
        route.setShipment(shipment);
        route.setLocationList(optimalRoute);

        // 6. Save the route to the database
        Route savedRoute = routeRepository.save(route);
        return savedRoute;
    }

    private List<Location> findShortestPath(Location originLocation, Location destinationLocation, Map<Location, Map<Location, Double>> distanceMatrix) {

        // Dijkstra's Algorithm implementation:

        Map<Location,Double> distances = new HashMap<>();
        Map<Location,Location> predecessors = new HashMap<>();
        Set<Location> visited = new HashSet<>();

        // Initialize distances and predecessors
        for(Location location : distanceMatrix.keySet()){
            distances.put(location,Double.MAX_VALUE);
            predecessors.put(location,null);
        }

        distances.put(originLocation,0.0);

        // Iterate until all locations are visited
        while(visited.size() < distanceMatrix.keySet().size()){
            // Find the location with the smallest distance that hasn't been visited
            Location current = findClosestUnvisitedLocation(distances,visited);
            visited.add(current);

            // Update distances to neighboring locations
            for(Location neighbor : distanceMatrix.get(current).keySet()){
                double tentiveDistance = distances.get(current) + distanceMatrix.get(current).get(neighbor);

                if(tentiveDistance < distances.get(neighbor)){
                    distances.put(neighbor,tentiveDistance);
                    predecessors.put(neighbor,current);
                }
            }
        }

        // Reconstruct the shortest path from destination to origin
        List<Location> shortestPath = new ArrayList<>();
        Location current = destinationLocation;

        while(current != null){
            shortestPath.add(current);
            current = predecessors.get(current);
        }
        Collections.reverse(shortestPath);

        return shortestPath;
    }

    private Location findClosestUnvisitedLocation(Map<Location, Double> distances, Set<Location> visited) {

        Location closest = null;

        double minDistance = Double.MAX_VALUE;
        for(Location location : distances.keySet()){
            if(!visited.contains(location) && distances.get(location) <  minDistance){
                closest = location;
                minDistance = distances.get(location);
            }
        }

        return closest;
    }

    private Map<Location, Map<Location, Double>> calculateDistanceMatrix(List<Location> allLocations) {
        Map<Location,Map<Location,Double>> distanceMatrix = new HashMap<>();

        for(Location origin : allLocations){
            Map<Location,Double> distanceFromOrigin = new HashMap<>();
            for(Location destination : allLocations){
                // Calculate distance using the Haversine formula
                double distance = calculateDistance(origin,destination);
                distanceFromOrigin.put(destination,distance);
            }
            distanceMatrix.put(origin,distanceFromOrigin);
        }
        
        return distanceMatrix;

    }

    // Haversine formula to calculate distance between two points on a sphere
    private double calculateDistance(Location origin, Location destination) {
        final int EARTH_RADIUS = 6371; // Radius of the Earth in kilometers

        double lat1 = Math.toRadians(origin.getLatitude());
        double lon1 = Math.toRadians(origin.getLongitude());
        double lat2 = Math.toRadians(destination.getLatitude());
        double lon2 = Math.toRadians(destination.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private Location geocodeAddress(String address) {

        return geoCodingService.geocodeAddress(address);
    }
}

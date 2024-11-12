

import java.io.*;
import java.util.*;

class BusStation {
    String name;
    double latitude;
    double longitude;

    public BusStation(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double haversine = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return 2 * R * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
    }
}

public class BusStationGraph {
    private Map<String, BusStation> stations = new HashMap<>();

    // Load bus stations from the .txt file
    public void loadStations(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0].trim();
            double latitude = Double.parseDouble(parts[1].trim());
            double longitude = Double.parseDouble(parts[2].trim());
            stations.put(name, new BusStation(name, latitude, longitude));
        }
        reader.close();
    }

    public void findNearestStation(double lat, double lon) {
        double minDistance = Double.MAX_VALUE;
        BusStation nearestStation = null;

        for (BusStation station : stations.values()) {
            double distance = BusStation.calculateDistance(lat, lon, station.latitude, station.longitude);
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }

        if (nearestStation != null) {
            System.out.println("Nearest station to (" + lat + ", " + lon + "): " + nearestStation.name);
            System.out.println("Distance: " + minDistance + " km");
        } else {
            System.out.println("No stations found.");
        }
    }

    public static void main(String[] args) throws IOException {
        BusStationGraph graph = new BusStationGraph();
        graph.loadStations("station.txt");

        double randomLat=15.394013608052845 ;
        double randomLon=75.02686456018097;

        graph.findNearestStation(randomLat, randomLon);
    }
}


//15.418911578959806, 75.05178148777884
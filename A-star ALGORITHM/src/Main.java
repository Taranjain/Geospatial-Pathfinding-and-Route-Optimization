package starlink;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get TLE file path from user
        System.out.println("Enter the path to the TLE file:");
        String tleFilePath = scanner.nextLine();

        // Parse TLE file to load all satellites
        List<Satellite> satellites = TLEParser.parseTLEFile(tleFilePath);
        System.out.println("Loaded " + satellites.size() + " satellites from the TLE file.");

        // Get user location input
        System.out.println("Enter the user's latitude:");
        double userLatitude = scanner.nextDouble();
        System.out.println("Enter the user's longitude:");
        double userLongitude = scanner.nextDouble();
        System.out.println("Enter the user's altitude (in meters):");
        double userAltitude = scanner.nextDouble();

        ECEFConverter userConverter = new ECEFConverter(userLatitude, userLongitude, userAltitude);
        Satellite userPosition = new Satellite("User", userConverter.getX(), userConverter.getY(), userConverter.getZ());

        // Get server location input
        System.out.println("Enter the server's latitude:");
        double serverLatitude = scanner.nextDouble();
        System.out.println("Enter the server's longitude:");
        double serverLongitude = scanner.nextDouble();
        System.out.println("Enter the server's altitude (in meters):");
        double serverAltitude = scanner.nextDouble();

        ECEFConverter serverConverter = new ECEFConverter(serverLatitude, serverLongitude, serverAltitude);
        Satellite serverPosition = new Satellite("Server", serverConverter.getX(), serverConverter.getY(), serverConverter.getZ());

        // Find the nearest satellite to the user and the server
        Satellite startSatellite = findNearestSatellite(userPosition, satellites);
        Satellite goalSatellite = findNearestSatellite(serverPosition, satellites);

        System.out.println("User's nearest satellite: " + startSatellite.getName());
        System.out.println("Server's nearest satellite: " + goalSatellite.getName());

        // Run A* algorithm to find the shortest path
        List<Satellite> path = AStarAlgorithm.findShortestPath(startSatellite, goalSatellite, satellites);

        // Calculate total distance of the path
        double totalDistance = calculateTotalDistance(path);

        // Output the path and total distance
        System.out.println("Path found:");
        for (Satellite satellite : path) {
            System.out.println(satellite.getName() + " (" + satellite.getX() + ", " + satellite.getY() + ", " + satellite.getZ() + ")");
        }
        System.out.printf("Total Path Distance: %.2f meters\n", totalDistance);
    }

    private static Satellite findNearestSatellite(Satellite position, List<Satellite> satellites) {
        Satellite nearestSatellite = null;
        double minDistance = Double.MAX_VALUE;

        for (Satellite satellite : satellites) {
            double distance = position.distanceTo(satellite);
            if (distance < minDistance) {
                minDistance = distance;
                nearestSatellite = satellite;
            }
        }
        return nearestSatellite;
    }

    private static double calculateTotalDistance(List<Satellite> path) {
        double totalDistance = 0.0;
        for (int i = 1; i < path.size(); i++) {
            totalDistance += path.get(i - 1).distanceTo(path.get(i));
        }
        return totalDistance;
    }
}



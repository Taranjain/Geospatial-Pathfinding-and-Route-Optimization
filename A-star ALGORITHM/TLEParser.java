import java.io.*;
import java.util.*;

public class TLEParser {
    public static List<Satellite> parseTLEFile(String filePath) {
        List<Satellite> satellites = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String name = line.trim(); // First line is the satellite name
                String line1 = br.readLine(); // TLE Line 1
                String line2 = br.readLine(); // TLE Line 2
                if (line1 != null && line2 != null) {
                    satellites.add(parseTLE(name, line1, line2));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading TLE file: " + e.getMessage());
        }
        return satellites;
    }

    private static Satellite parseTLE(String name, String line1, String line2) {
        double inclination = Double.parseDouble(line2.substring(8, 16).trim());
        double rightAscension = Double.parseDouble(line2.substring(17, 25).trim());
        double meanMotion = Double.parseDouble(line2.substring(52, 63).trim());

        // Estimate altitude based on mean motion
        double altitude = 6371 + (86400 / meanMotion) - 6371;
        double lat = inclination;
        double lon = rightAscension;

        ECEFConverter ecefConverter = new ECEFConverter(lat, lon, altitude * 1000); // Convert to meters
        return new Satellite(name, ecefConverter.getX(), ecefConverter.getY(), ecefConverter.getZ());
    }
}

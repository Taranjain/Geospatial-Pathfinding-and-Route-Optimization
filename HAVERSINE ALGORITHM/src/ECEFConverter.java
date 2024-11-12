public class ECEFConverter {
    private double x, y, z;

    public ECEFConverter(double latitude, double longitude, double altitude) {
        convertToECEF(latitude, longitude, altitude);
    }

    private void convertToECEF(double latitude, double longitude, double altitude) {
        final double a = 6378137.0; // Earth's semi-major axis in meters
        final double f = 1 / 298.257223563; // Flattening factor
        final double e2 = f * (2 - f); // Square of eccentricity

        double radLat = Math.toRadians(latitude);
        double radLon = Math.toRadians(longitude);

        double N = a / Math.sqrt(1 - e2 * Math.sin(radLat) * Math.sin(radLat));
        this.x = (N + altitude) * Math.cos(radLat) * Math.cos(radLon);
        this.y = (N + altitude) * Math.cos(radLat) * Math.sin(radLon);
        this.z = (N * (1 - e2) + altitude) * Math.sin(radLat);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}

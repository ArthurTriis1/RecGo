package br.edu.ufpe.recife.tads.recgo.utils;

public class DIstanceCalculator {
    private static final double EARTH_RADIUS_KM = 6371;

    private static final double MINIMAL_DISTANCE_FOR_ACCESS_IN_METERS = 15;

    public static double calcDistanceInMeters(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude){
        // Conversão de graus pra radianos das latitudes
        double firstLatToRad = Math.toRadians(firstLatitude);
        double secondLatToRad = Math.toRadians(secondLatitude);

        // Diferença das longitudes
        double deltaLongitudeInRad = Math.toRadians(secondLongitude
                - firstLongitude);

        // Cálcula da distância entre os pontos
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad))
                * EARTH_RADIUS_KM
                * 1000;
    }

    public static boolean canAccess(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude){
        return MINIMAL_DISTANCE_FOR_ACCESS_IN_METERS >= calcDistanceInMeters(firstLatitude,firstLongitude,secondLatitude,secondLongitude);
    }
}

package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String locationsPath = "src/main/resources/locations/locations.json";
        String regionsPath = "src/main/resources/locations/regions.json";
        String resultPath = "src/main/resources/locations/result.json";

        try {
            String locationJSON = new String(Files.readAllBytes(Paths.get(locationsPath)));
            JSONArray locations = new JSONArray(locationJSON);
            String regionJSON = new String(Files.readAllBytes(Paths.get(regionsPath)));
            JSONArray regions = new JSONArray(regionJSON);


            JSONArray resultsJSON = new JSONArray();

            for (int i = 0; i < regions.length(); i++){
                JSONObject region = regions.getJSONObject(i);
                String regionName = region.getString("name");
                JSONArray regionCoordinatesArray = region.getJSONArray("coordinates");

                JSONObject regionMatches = new JSONObject();
                regionMatches.put("region", regionName);
                JSONArray regionLocations = new JSONArray();

                for (int j = 0; j < locations.length(); j++){
                    JSONObject location = locations.getJSONObject(j);
                    String locationName = location.getString("name");
                    JSONArray locationCoordinates = location.getJSONArray("coordinates");

                    if (isLocationInsideRegion(locationCoordinates, regionCoordinatesArray)){
                        regionLocations.put(locationName);
                    }
                }

                regionMatches.put("matched_locations", regionLocations);
                resultsJSON.put(regionMatches);
            }

            Files.write(Paths.get(resultPath), resultsJSON.toString(2).getBytes());



        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }


    public static boolean isLocationInsideRegion(JSONArray locationCoordinates, JSONArray regionCoordinatesArray) {
        double x = locationCoordinates.getDouble(0);
        double y = locationCoordinates.getDouble(1);

        for (int i = 0; i < regionCoordinatesArray.length(); i++){
            JSONArray coordinates = regionCoordinatesArray.getJSONArray(i);
            if (regionParameter(coordinates, x, y)){
                return true;
            }
        }

        return false;
    }


    public static boolean regionParameter(JSONArray coordinates, double x, double y) {
        ArrayList<Double> xCoords=  new ArrayList<>();
        ArrayList<Double> yCoords=  new ArrayList<>();

        for (int i = 0; i < coordinates.length(); i++){
            JSONArray coordinate = coordinates.getJSONArray(i);
            double xCoordinate = coordinate.getDouble(0);
            double yCoordinate = coordinate.getDouble(1);
            xCoords.add(xCoordinate);
            yCoords.add(yCoordinate);
        }

        return (x >= min(xCoords) && x <= max(xCoords) && y >= min(yCoords) && y <= max(yCoords));

    }

    public static double min(List<Double> arr){
        double min = Double.MAX_VALUE;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < min) {
                min = arr.get(i);
            }
        }
        return min;
    }

    public static double max(List<Double> arr){
        double max = Double.MIN_VALUE;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }
        return max;
    }

}
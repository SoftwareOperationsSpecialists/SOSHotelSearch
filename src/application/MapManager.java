package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.javafx.MapView;

public class MapManager {
  protected final static String API_KEY = "AIzaSyChDY-krlOCW7QcWSoQndEoazJndJaOyyw"; // API key for Google Maps
  private final static double DEGREES_TO_MILES = 69; // const approx num of miles in one degree
  private int WAIT_TIME = 2; // const wait time for function delay in seconds

  private double defaultZoom; // defines the default zoom level
  private double radiusInMiles; // defines radius (in miles) to display hotels from searched for
  private double radiusInDegrees;

  private String iconFilePath = "src/application/mapicons/marker_icon.png";
  private String hotelFilePath = "src/application/hotels.txt";

  private MapView mapView;
  private MapViewOptions mapViewOptions;
  private Icon icon;

  private String address;
  private String error;

  private boolean hasError;
  private ArrayList<String> hotelsToDisplay = new ArrayList<>();

  public MapManager() {
    MapView.InitJavaFX();
    mapViewOptions = new MapViewOptions();
    mapViewOptions.setApiKey(API_KEY);
    defaultZoom = 14.0;
    radiusInMiles = 20;
    radiusInDegrees = radiusInMiles / DEGREES_TO_MILES;
    icon = new Icon();
    icon.loadFromFile(iconFilePath);
    mapView = new MapView(mapViewOptions);
    address = "Fort Myers, FL";
    error = "No error";
    hasError = false;
  }

  public void setDefaultZoom(double defaultZoom){
    this.defaultZoom = defaultZoom;
  }

  public void setRadiusInMiles(double radiusInMiles) {
    this.radiusInMiles = radiusInMiles;
  }

  public void setIconFilePath(String iconFilePath) {
    this.iconFilePath = iconFilePath;
    this.icon.loadFromFile(iconFilePath);
  }

  public void setHotelFilePath(String hotelFilePath) {
    this.hotelFilePath = hotelFilePath;
  }

  public void setMapView(MapView mapView) {
    this.mapView = mapView;
  }

  public void setMapViewOptions(MapViewOptions mapViewOptions) {
    this.mapViewOptions = mapViewOptions;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  private void setError(String error) {
    this.error = error;
  }

  private void setErrorStatus(boolean status) {
    this.hasError = status;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getDefaultZoom() {
    return defaultZoom;
  }

  public double getRadiusInMiles() {
    return radiusInMiles;
  }

  public double getRadiusInDegrees() {
    return  radiusInDegrees;
  }

  public String getIconFilePath() {
    return iconFilePath;
  }

  public String getHotelFilePath() {
    return hotelFilePath;
  }

  public MapView getMapView() {
    return mapView;
  }

  public MapViewOptions getMapViewOptions() {
    return mapViewOptions;
  }

  public String getAddress() {
    return address;
  }

  public String getError() {
    return error;
  }

  public boolean getErrorStatus() {
    return hasError;
  }

  public ArrayList getHotelsToDisplay() { return hotelsToDisplay; }

  public void createMap() {
    //create map ready handler
    mapView.setOnMapReadyHandler(new MapReadyHandler() {
      @Override
      public void onMapReady(MapStatus status) {
        if (status == MapStatus.MAP_STATUS_OK) {
          final Map map = mapView.getMap();
          map.setZoom(defaultZoom);
          GeocoderRequest request = new GeocoderRequest();
          request.setAddress(address);

          mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
              if (status == GeocoderStatus.OK) {
                map.setCenter(result[0].getGeometry().getLocation());

                double locLatRange = result[0].getGeometry().getLocation().getLat();
                double locLonRange = result[0].getGeometry().getLocation().getLng();

                try {
                  BufferedReader br = new BufferedReader(new FileReader(hotelFilePath));
                  br.readLine(); //ignore the first line

                  while (br.readLine() != null) {
                    try {
                      String line = br.readLine();
                      String[] lineSplit = line.split(",");
                      double lat = Double.parseDouble(lineSplit[6]);
                      double lon = Double.parseDouble(lineSplit[7]);

                      // compare the difference between the coordinates of the location the user searched for
                      // and every hotel in the database. If that difference is at most 'radius' degrees,
                      // display the hotel on the map.
                      if (Math.abs(Math.abs(lat) - Math.abs(locLatRange)) <= radiusInDegrees &&
                              Math.abs(Math.abs(lon) - Math.abs(locLonRange)) <= radiusInDegrees) {
                        Marker marker = new Marker(map);
                        marker.setIcon(icon);
                        marker.setPosition(new LatLng(lat, lon));

                        hotelsToDisplay.add(line);
                      }
                    } catch (NullPointerException e) {
                      e.printStackTrace();
                    }
                  }
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
              else if (status == GeocoderStatus.ERROR) {
                setError("There was an error connecting to the Google Servers.");
                setErrorStatus(true);
              }
              else if (status == GeocoderStatus.INVALID_REQUEST) {
                setError("This request was invalid.");
                setErrorStatus(true);
              }
              else if (status == GeocoderStatus.OVER_QUERY_LIMIT) {
                setError("The web page has gone over the requests limit in too short a period of time.");
                setErrorStatus(true);
              }
              else if (status == GeocoderStatus.REQUEST_DENIED) {
                setError("Access denied.");
                setErrorStatus(true);
              }
              else if (status == GeocoderStatus.UNKNOWN_ERROR) {
                setError("Your request could not be processed due to a server error.");
                setErrorStatus(true);
              }
              else {
                setError("No results found for this location.");
                setErrorStatus(true);
              }
            }
          });
        }
        else {
          setError("Map status error.");
          setErrorStatus(true);
        }
      }
    });
    try {
      TimeUnit.SECONDS.sleep(WAIT_TIME); // wait WAIT_TIME seconds to finish parsing the text file
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

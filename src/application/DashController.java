package application;

import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.javafx.MapView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DashController {
  private final String API_KEY = "AIzaSyC5NEnXd1whX4ZgFoKTpKrrC5IH4LYRIME"; // API key for Google Maps
  private final double DEGREES_TO_MILES = 69; // const approx num of miles in one degree

  private double default_zoom = 14.0; // defines the default zoom level
  private double radius = 20 / DEGREES_TO_MILES; // defines radius (in degrees) to display hotels from searched for
                                                 // location on map. Integer literal represents radius in miles.

  private String iconFilePath = "src/application/mapicons/marker_icon.png";
  private String hotelFilePath = "src/application/hotels.txt";

  boolean isNewInstance = true; // intended to set map location from previous screen on startup. Flagged false
                                // after this has been done once

  @FXML
  private BorderPane mapPane;

  @FXML
  private VBox mapBox;

  @FXML
  private TextField searchBar;

  public void initialize() {
    // setup map stuff
    MapView.InitJavaFX();
    MapViewOptions mapViewOptions = new MapViewOptions();
    mapViewOptions.setApiKey(API_KEY);
    Icon icon = new Icon();
    icon.loadFromFile(iconFilePath);
    final MapView mapView = new MapView(mapViewOptions);

    //create map ready handler
    mapView.setOnMapReadyHandler(new MapReadyHandler() {
      @Override
      public void onMapReady(MapStatus status) {
        if (status == MapStatus.MAP_STATUS_OK) {
          final Map map = mapView.getMap();
          map.setZoom(default_zoom);
          GeocoderRequest request = new GeocoderRequest();

          // if opened for first time, set location to location from previous screen
          // else, user is searching again, set location to search bar location
          if (isNewInstance) {
            request.setAddress(SearchController.getLocation());
          }
          else {
            request.setAddress(searchBar.getText());
          }
          isNewInstance = false;

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
                      String[] line = br.readLine().split(",");
                      double lat = Double.parseDouble(line[5]);
                      double lon = Double.parseDouble(line[6]);

                      // compare the difference between the coordinates of the location the user searched for
                      // and every hotel in the database. If that difference is at most 'radius' degrees,
                      // display the hotel on the map.
                      if (Math.abs(Math.abs(lat) - Math.abs(locLatRange)) <= radius &&
                          Math.abs(Math.abs(lon) - Math.abs(locLonRange)) <= radius) {
                        Marker marker = new Marker(map);
                        marker.setIcon(icon);
                        marker.setPosition(new LatLng(lat, lon));
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
                System.out.println("ERROR: There was an error connecting to the Google Servers.");
              }
              else if (status == GeocoderStatus.INVALID_REQUEST) {
                System.out.println("ERROR: This request was invalid.");
              }
              else if (status == GeocoderStatus.OVER_QUERY_LIMIT) {
                System.out.println("ERROR: The web page has gone over the requests limit in too short a period of time.");
              }
              else if (status == GeocoderStatus.REQUEST_DENIED) {
                System.out.println("ERROR: Access denied.");
              }
              else if (status == GeocoderStatus.UNKNOWN_ERROR) {
                System.out.println("ERROR: Your request could not be processed due to a server error.");
              }
              else {
                System.out.println("ERROR: No results found for this location.");
              }
            }
          });
        }
      }
    });

    mapPane = new BorderPane(mapView);
    mapPane.setPrefHeight(900.0);
    mapBox.getChildren().add(mapPane);
  }

  public void HotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }

  public void HighToLow(ActionEvent event) throws Exception{

  }

  public void savedHotels(ActionEvent event) throws Exception {
    Parent Saved = FXMLLoader.load(getClass().getResource("SavedHotels.fxml"));
    Scene savedScene = new Scene(Saved);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(savedScene);
    window.show();
  }

  public void logout(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
}

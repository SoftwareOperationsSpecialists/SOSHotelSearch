package application;

import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;
import com.teamdev.jxmaps.LatLng;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
  @FXML
  private Pane mapPane; //this needs to be implemented
  // the mapView object just needs to be placed on this pane but I cannot get it to show up

  public void HotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    // setup map stuff
    MapViewOptions mapViewOptions = new MapViewOptions();
    mapViewOptions.setApiKey(API_KEY);
    Icon icon = new Icon();
    icon.loadFromFile("mapicons/marker_icon.png");
    final MapView mapView = new MapView(mapViewOptions);

    mapView.setOnMapReadyHandler(new MapReadyHandler() {
      @Override
      public void onMapReady(MapStatus status) {
        if (status == MapStatus.MAP_STATUS_OK) {
          final Map map = mapView.getMap();
          map.setZoom(default_zoom);
          GeocoderRequest request = new GeocoderRequest();
          request.setAddress("Disney World");

          mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
              if (status == GeocoderStatus.OK) {
                map.setCenter(result[0].getGeometry().getLocation());

                double locLatRange = result[0].getGeometry().getLocation().getLat();
                double locLonRange = result[0].getGeometry().getLocation().getLng();

                try {
                  BufferedReader br = new BufferedReader(new FileReader("hotels.txt"));
                  br.readLine(); //ignore the first line

                  while (br.readLine() != null) {
                    try {
                      String[] line = br.readLine().split(",");
                      double lat = Double.parseDouble(line[5]);
                      double lon = Double.parseDouble(line[6]);

                      // compares the difference between the coordinates of the location the user searched for
                      // and every hotel in the database. If that difference is at most 'radius' degrees,
                      // display the hotel on the map.
                      if (Math.abs(Math.abs(lat)-Math.abs(locLatRange)) <= radius &&
                          Math.abs(Math.abs(lon)-Math.abs(locLonRange)) <= radius) {
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
            }
          });
        }
      }
    });

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

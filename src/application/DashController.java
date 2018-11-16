package application;

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

public class DashController {
    private boolean isNewInstance = true; // intended to set map location from previous screen on startup. Flagged false

  @FXML
  private BorderPane mapPane;

  @FXML
  private VBox mapBox;

  @FXML
  private TextField searchBar;

  public void initialize() {
    MapDriver mapDriver = new MapDriver();
    if (isNewInstance) {
      mapDriver.setAddress(SearchController.getLocation());
    }
    else {
      mapDriver.setAddress(searchBar.getText());
    }

    isNewInstance = false;
    mapDriver.createMap();

    if (!mapDriver.getErrorStatus()) {
      mapPane = new BorderPane(mapDriver.getMapView());
      mapPane.setPrefHeight(900.0);
      mapBox.getChildren().add(mapPane);
    }
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

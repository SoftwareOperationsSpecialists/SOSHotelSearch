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

public class SearchController {

  private boolean isNewInstance = true; // intended to set map location from previous screen on startup. Flagged false
  private int offset = 0;

  @FXML
  private BorderPane mapPane;

  @FXML
  private VBox mapBox;

  @FXML
  private TextField searchBar;

  //Side panel buttons
  public void Dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void MyAccount(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void logout(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }


  public void initialize() {
    MapDriver mapDriver = new MapDriver();
    if (isNewInstance) {
      mapDriver.setAddress(DashController.getLocation());
    } else {
      mapDriver.setAddress(searchBar.getText());
    }

    isNewInstance = false;
    mapDriver.createMap();

    if (!mapDriver.getErrorStatus()) {
      mapPane.getChildren().clear();
      mapPane = new BorderPane(mapDriver.getMapView());

      // for some reason the prefHeight has to be set manually and also shifted up every time the map
      // is redrawn. Unsure of reason. Please ignore magic numbers.
      mapPane.setPrefHeight(900.0 + offset);
      offset += 500;

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

  public void HighToLow(ActionEvent event) throws Exception {

  }


}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashController {
  @FXML
  private TextField searchBar;
  private static String location;

  @FXML
  private MenuButton navigationBtn;

  @FXML
  private MenuItem myAccountItem;
  @FXML
  private MenuItem mySavedHotelItem;
  @FXML
  private MenuItem myReservationItem;
  @FXML
  private MenuItem logoutItem;
  @FXML
  private Label status;

  @FXML
  private Spinner roomCount;
  private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0,9,1);

//Side Panel buttons
  public void MyAccount(ActionEvent event) throws Exception {
  Parent Logout = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
  Scene logoutScene = new Scene(Logout);

  Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
  window.setScene(logoutScene);
  window.show();
}

  public void savedHotels(ActionEvent event) throws Exception {
    Parent Saved = FXMLLoader.load(getClass().getResource("SavedHotels.fxml"));
    Scene savedScene = new Scene(Saved);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(savedScene);
    window.show();
  }
  public void logout(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

  public void Search(javafx.event.ActionEvent event) throws Exception {
    location = searchBar.getText();

    if (!location.isEmpty()) {
      MapManager mapDriver = new MapManager();
      mapDriver.setAddress(location);
      mapDriver.createMap();

      if (mapDriver.getErrorStatus())   {
        status.setText("Error"); // this should be set to the text of a label
      }
      else {
        Parent Dashboard = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene dashboard = new Scene(Dashboard);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboard);
        window.show();
      }
    }
    else {
      status.setText("Error"); // display error message
    }
  }

  public static String getLocation() {
    return location;
  }

  public void modifyRoom() {
    this.roomCount.setValueFactory(roomCountFactory);
  }

  public void changeScene(){
  }

}

package application;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

public class SearchController {

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
  private Spinner roomCount;
  private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0,9,1);

  public void logout(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

  public void Search(ActionEvent event) throws Exception {
    //navigationBtn.
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene dashboard = new Scene(Dashboard);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

  public void modifyRoom() {
    this.roomCount.setValueFactory(roomCountFactory);
  }

  public void changeScene(){
  }

}

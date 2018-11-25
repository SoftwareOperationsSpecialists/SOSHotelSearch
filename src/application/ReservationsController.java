package application;

import application.HotelOwnerController.Info;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ReservationsController implements Initializable {

  @FXML
  private TableView<application.HotelOwnerController.Info> tableView;
  @FXML
  private TableColumn<application.HotelOwnerController.Info, Integer> HotelNameCol;
  @FXML
  private TableColumn<application.HotelOwnerController.Info, String> CheckInCol;
  @FXML
  private TableColumn<application.HotelOwnerController.Info, String> CheckOutCol;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public static class Info {

    // Class constructor takes in fields as parameters

  }
  public void myAccount(ActionEvent event) throws Exception {
    Parent Account = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
    Scene account = new Scene(Account);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(account);
    window.show();
  }
  public void Dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
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
}

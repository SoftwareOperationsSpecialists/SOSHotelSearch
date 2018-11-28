package application;

import application.HotelOwnerController.Info;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReservationsController implements Initializable {

  ObservableList<Data> list = FXCollections.observableArrayList();
  static String url = "jdbc:derby:lib/SOSHotelAccountDB";

  @FXML
  private TableView<Data> tableView;
  @FXML
  private TableColumn<Data, Integer> HotelNameCol;
  @FXML
  private TableColumn<Data, String> CheckInCol;
  @FXML
  private TableColumn<Data, String> CheckOutCol;
  @FXML
  private Label status;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initCol();
    addData();

  }

  public static class Data {
    private final SimpleStringProperty HotelName;
    private final SimpleStringProperty CheckIn;
    private final SimpleStringProperty CheckOut;
    // Class constructor takes in fields as parameters
    Data(String name, String checkIn, String checkOut) {
      this.HotelName = new SimpleStringProperty(name);
      this.CheckIn = new SimpleStringProperty(checkIn);
      this.CheckOut = new SimpleStringProperty(checkOut);
    }
    public String getName(){ return HotelName.get();}
    public String getCheckIn(){ return CheckIn.get();}
    public String getCheckOut(){ return CheckOut.get();}
  }
  private void initCol() {
    HotelNameCol.setCellValueFactory(new PropertyValueFactory<>("HotelName"));
    CheckInCol.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
    CheckOutCol.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));

  }
  private void addData() {
    final String JOIN_Hotels = "SELECT NAME, CHECKINDATE, "
        + "CHECKOUTDATE"
        + " FROM SOS.HOTEL INNER JOIN "
        + "SOS.RESERVATIONS ON RESERVATIONS.ID=HOTEL.ID";
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_Hotels)) {

      // Values from Resultset object are added into list
      while(resultSet.next()) {
        String HotelName = resultSet.getString("name");
        String CheckIn = resultSet.getString("checkindate");
        String CheckOut = resultSet.getString("checkoutdate");

        list.add(new Data(HotelName, CheckIn,CheckOut));
      }
    }
    catch (SQLException e) {
      status.setText("Error");
      e.printStackTrace();
    }
    // Associates tableView with list items
    tableView.getItems().setAll(list);

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


package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationsController implements Initializable {

  private ObservableList<Data> list = FXCollections.observableArrayList();
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
    final String JOIN_Hotels = "SELECT NAME, CHECKIN, "
        + "CHECKOUT"
        + " FROM SOS.HOTEL INNER JOIN "
        + "SOS.RESERVATIONS ON SOS.RESERVATIONS.ID=SOS.HOTEL.ID";
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_Hotels)) {

      // Values from Resultset object are added into list
      while(resultSet.next()) {
        String HotelName = resultSet.getString("NAME");
        String CheckIn = resultSet.getString("checkin");
        String CheckOut = resultSet.getString("checkout");

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
    Navigator.myAccount(event);
  }
  public void dashboard(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }
  public void savedHotels(ActionEvent event) throws Exception {
    Navigator.savedHotels(event);
  }
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }
}


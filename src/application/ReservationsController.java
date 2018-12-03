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

/**
 * Desc: controller class for the reservations scene
 */

public class ReservationsController implements Initializable {

  private ObservableList<Data> list = FXCollections.observableArrayList();
  static String url = "jdbc:derby:lib/SOSHotelAccountDB";

  @FXML
  private TableView<Data> tableView;
  @FXML
  private TableColumn<Data, String> HotelNameCol;
  @FXML
  private TableColumn<Data, String> CheckInCol;
  @FXML
  private TableColumn<Data, String> CheckOutCol;
  @FXML
  private Label status;

  /**
   * Desc: intializes the scene by setting up columns and adding data
   * @param: location - location of the database
   * @param resources - a ResourceBundle object
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    initCol();
    addData();
  }

  /**
   * Desc: static inner class defining the data
   */
  public static class Data {
    private final SimpleStringProperty HotelName;
    private final SimpleStringProperty CheckIn;
    private final SimpleStringProperty CheckOut;
    
    /**
     * Desc: Data constructor
     * @param: name - the name of the hotel
     * @param: checkIn - the check in date
     * @param: checkout - the check out date
     */
    Data(String name, String checkIn, String checkOut) {
      this.HotelName = new SimpleStringProperty(name);
      this.CheckIn = new SimpleStringProperty(checkIn);
      this.CheckOut = new SimpleStringProperty(checkOut);
    }
    
    /**
     * Desc: returns the hotel name
     * @return HotelName - the hotel name
     */
    public String getName() { 
      return HotelName.get();
    }
   
    /**
     * Desc: returns the check in date
     * @return CheckIn - the check in date
     */
    public String getCheckIn() { 
      return CheckIn.get();
    }
    
    /**
     * Desc: returns the check out date
     * @return CheckOut - the check out date
     */
    public String getCheckOut() { 
      return CheckOut.get();
    }
  }
  
  /**
   * Desc: initializes column data in the table
   */
  private void initCol() {
    HotelNameCol.setCellValueFactory(new PropertyValueFactory<>("HotelName"));
    CheckInCol.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
    CheckOutCol.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));
  }
  
  /**
   * Desc: gets data from the database to add to the table
   */
  private void addData() {
    final String JOIN_Hotels = "SELECT SOS.HOTEL.NAME, SOS.RESERVATIONS.CHECKIN, "
        + "SOS.RESERVATIONS.CHECKOUT"
        + " FROM SOS.RESERVATIONS INNER JOIN "
        + "SOS.HOTEL ON SOS.RESERVATIONS.HOTEL_ID=SOS.HOTEL.ID";
    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_Hotels)) {

      // Values from Resultset object are added into list
      while(resultSet.next()) {
        String HotelName = resultSet.getString("name");
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
  
  /**
   * Desc: goes to myAccount scene
   * @param: event - ActionEvent from the button
   * @throws exception
   */
  public void myAccount(ActionEvent event) throws Exception {
    Navigator.myAccount(event);
  }
  
  /**
   * Desc: goes to dashbard scene
   * @param: event - ActionEvent from the button
   * @throws exception
   */
  public void dashboard(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }
  
  /**
   * Desc: goes to logout scene
   * @param: event - ActionEvent from the button
   * @throws exception
   */
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }
}


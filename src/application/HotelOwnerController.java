package application;

import javafx.beans.property.SimpleIntegerProperty;
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

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HotelOwnerController implements Initializable {

  ObservableList<Info> list = FXCollections.observableArrayList();

  @FXML
  private TableView<Info> tableView;
  @FXML
  private TableColumn<Info, Integer> NameCol;
  @FXML
  private TableColumn<Info, String> CheckInCol;
  @FXML
  private TableColumn<Info, String> CheckOutCol;
  @FXML
  private TableColumn<Info, String> NumRoomsCol;
  @FXML
  private Label status;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // function will initialize column values
    initCol();
    addData();
  }

  public static class Info {
    private final SimpleStringProperty Name;
    private final SimpleStringProperty CheckIn;
    private final SimpleStringProperty CheckOut;
    private final SimpleIntegerProperty NumRoom;
    // Class constructor takes in fields as parameters
    Info(String name, String checkIn, String checkOut, Integer numRoom) {
      this.Name = new SimpleStringProperty(name);
      this.CheckIn = new SimpleStringProperty(checkIn);
      this.CheckOut = new SimpleStringProperty(checkOut);
      this.NumRoom = new SimpleIntegerProperty(numRoom);
    }
    public String getName(){ return Name.get();}
    public String getCheckIn(){ return CheckIn.get();}
    public String getCheckOut(){ return CheckOut.get();}
    public Integer getNumRoom(){ return NumRoom.get();}
  }
  private void initCol() {
    NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
    CheckInCol.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
    CheckOutCol.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));
    NumRoomsCol.setCellValueFactory(new PropertyValueFactory<>("NumRoom"));

  }
  private void addData() {
    final String JOIN_RECIPES = "SELECT SOS.hotel.name, sos.reservations.checkin, "
        + "sos.reservations.checkout, sos.hotel.rooms"
        + " FROM sos.hotel INNER JOIN "
        + "sos.reservations ON sos.reservations.HOTEL_ID=sos.hotel.id";
    try (Connection connection = DriverManager.getConnection(Credentials.url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(JOIN_RECIPES)) {

      // Values from Resultset object are added into list
      while(resultSet.next()) {
        String Name = resultSet.getString("name");
        String CheckIn = resultSet.getString("checkin");
        String CheckOut = resultSet.getString("checkout");
        Integer NumRooms = resultSet.getInt("rooms");

        list.add(new Info(Name, CheckIn,CheckOut, NumRooms));
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    // Associates tableView with list items
    tableView.getItems().setAll(list);

  }

  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }
}


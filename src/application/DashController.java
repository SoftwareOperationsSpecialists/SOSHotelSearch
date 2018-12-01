package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashController implements Initializable {
    @FXML
    private TextField searchBar;
    private static String location;

    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;

    @FXML
    private Label searchStatus;
    @FXML
    private Spinner roomCount;
    
    private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0, 9, 1);
    private static LocalDate userCheckInDate;
    private static LocalDate userCheckOutDate;
    private static int numOfRooms;

    //Side Panel buttons
    public void MyAccount(ActionEvent event) throws Exception {
        Navigator.myAccount(event);
    }

    public void savedHotels(ActionEvent event) throws Exception {
        Navigator.savedHotels(event);
    }

    public void logout(ActionEvent event) throws Exception {
        Navigator.logout(event);
    }

    public void search(ActionEvent event) throws Exception {
        location = searchBar.getText();

        if (location.isEmpty()) {
            searchStatus.setText("Must input Location");

        } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            searchStatus.setText("Please select check in and check out date!");

        } else {
            MapManager mapManager = new MapManager();
            mapManager.setAddress(location);
            mapManager.createMap();
            userCheckInDate = checkInDate.getValue();
            userCheckOutDate = checkOutDate.getValue();
            numOfRooms = (int) roomCount.getValue();
            Navigator.search(event);

            if (mapManager.getErrorStatus()) {
                searchStatus.setText(mapManager.getError()); // this should be set to the text of a label
            }
        }
    }

    public static String getLocation() {
        return location;
    }

    static LocalDate getUserCheckInDate() {
        return userCheckInDate;
    }

    static LocalDate getUserCheckOutDate() {
      return userCheckOutDate;
    }

    static int getNumOfRooms() {
      return numOfRooms;
    }

    public void modifyRoom() {
        this.roomCount.setValueFactory(roomCountFactory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomCount.setValueFactory(roomCountFactory);
    }
}


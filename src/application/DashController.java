package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
    private static Navigator navigator = new Navigator();
    private static LocalDate userCheckInDate;
    private static LocalDate userCheckOutDate;
    private static int numOfRooms;

    //Side Panel buttons
    public void MyAccount(ActionEvent event) throws Exception {
        navigator.myAccount(event);
    }

    public void savedHotels(ActionEvent event) throws Exception {
        navigator.savedHotels(event);
    }

    public void logout(ActionEvent event) throws Exception {
        navigator.logout(event);
    }

    public void Search(ActionEvent event) throws Exception {
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
            navigator.search(event);

            if (mapManager.getErrorStatus()) {
                searchStatus.setText(mapManager.getError()); // this should be set to the text of a label
            }
        }
    }

    public static String getLocation() {
        return location;
    }

    public static LocalDate getUserCheckInDate() {
      return userCheckInDate;
    }

    public static LocalDate getUserCheckOutDate() {
      return userCheckOutDate;
    }

    public static int getNumOfRooms() {
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


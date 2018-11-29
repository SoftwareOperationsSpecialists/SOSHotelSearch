package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

//functionality for the initial search from the dashboard
public class DashController implements Initializable {
    //initialization
    @FXML
    private TextField searchBar;        //searchbar where location is to be entered
    private static String location;     //location

    @FXML
    private DatePicker checkInDate;     //check-in date
    @FXML
    private DatePicker checkOutDate;    //check-out date

    @FXML
    private Label searchStatus;         //notifies the user if they try to search without filling in all required fields
    @FXML
    private Spinner roomCount;          //spinner for number of rooms
    
    private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0, 9, 1);  //allows user to inc/dec # of rooms
    private static Navigator navigator = new Navigator();   //allows moving to another scene
    private static LocalDate userCheckInDate;               //stores the check-in date chosen by the user
    private static LocalDate userCheckOutDate;              //stores the check-out date chosen by the user
    private static int numOfRooms;                          //stores the number of rooms chosen by the user

    //Side Panel buttons
    public void MyAccount(ActionEvent event) throws Exception {
        Navigator.myAccount(event);         // goes to My Account screen
    }

    public void savedHotels(ActionEvent event) throws Exception {
        Navigator.savedHotels(event);       // goes to saved hotels screen
    }

    public void logout(ActionEvent event) throws Exception {
        Navigator.logout(event);            // goes to login screen
    }

    //Search
    public void Search(ActionEvent event) throws Exception {
        location = searchBar.getText();     // location variable becomes what is written in the searchbar

        //checks if a location was entered
        if (location.isEmpty()) {
            searchStatus.setText("Must input Location");

        //checks if dates were entered
        } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            searchStatus.setText("Please select check in and check out date!");

        //all information was entered, so generates the map and goes to the map scene
        } else {
            MapManager mapManager = new MapManager();
            mapManager.setAddress(location);                // sets map location
            mapManager.createMap();                         // creates map
            userCheckInDate = checkInDate.getValue();       // gets check-in date
            userCheckOutDate = checkOutDate.getValue();     // gets check-out date
            numOfRooms = (int) roomCount.getValue();        // gets number of rooms
            Navigator.search(event);                        // go to search screen

            //checks for error
            if (mapManager.getErrorStatus()) {
                searchStatus.setText(mapManager.getError()); // this should be set to the text of a label
            }
        }
    }
    
    //gets location
    public static String getLocation() {
        return location;
    }

    //gets check-in date
    static LocalDate getUserCheckInDate() {
      return userCheckInDate;
    }

    //gets check-out date
    static LocalDate getUserCheckOutDate() {
      return userCheckOutDate;
    }

    //gets number of rooms
    static int getNumOfRooms() {
      return numOfRooms;
    }

    //sets number of rooms
    public void modifyRoom() {
        this.roomCount.setValueFactory(roomCountFactory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomCount.setValueFactory(roomCountFactory);
    }
}


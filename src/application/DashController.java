package application;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

import java.io.IOException;
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

    @FXML
    private ProgressIndicator indicator;
    
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
            // setup a new task for UI thread to run while loading the new scene
            Task<Parent> loadAnimation = new Task<Parent>() {
                @Override
                public Parent call() throws IOException {
                    searchStatus.setVisible(false);
                    indicator.setVisible(true);

                    // dummy data because the function needs to have the parent returned
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                    Parent root = loader.load();
                    return root;
                }
            };

            loadAnimation.setOnSucceeded(successEvent -> {
                indicator.setVisible(false);
                searchStatus.setVisible(true);
                MapManager mapManager = new MapManager();
                mapManager.setAddress(location);                // sets map location
                mapManager.createMap();                         // creates map
                userCheckInDate = checkInDate.getValue();       // gets check-in date
                userCheckOutDate = checkOutDate.getValue();     // gets check-out date
                numOfRooms = (int) roomCount.getValue();        // gets number of rooms

                //checks for error
                if (mapManager.getErrorStatus()) {
                    searchStatus.setText(mapManager.getError());
                } else {
                    searchStatus.setVisible(false);
                    try {
                        Navigator.search(event);                      // go to search screen
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            loadAnimation.setOnFailed(e -> loadAnimation.getException().printStackTrace());

            Thread thread = new Thread(loadAnimation);
            thread.start();
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


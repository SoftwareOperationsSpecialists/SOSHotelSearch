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
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;

/**
* Desc: allows user to start a search by entering location,
*   check-in date, check-out date, and number of rooms
*/
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
  /**
  * goes to my account scene
  */
  public void MyAccount(ActionEvent event) throws Exception {
    Navigator.myAccount(event);
  }
  /**
  * goes to logout scene
  */
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }

  /**
  * Desc: performs a search by getting the location from the searchbar
  *   when the user clicks the search button
  */
  public void search(ActionEvent event) throws Exception {
    location = searchBar.getText();

    if (location.isEmpty()) {
      searchStatus.setText("Please enter a location.");

    } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
      searchStatus.setText("Please select check-in and check-out dates.");

    } else if (checkInDate.getValue().compareTo(checkOutDate.getValue()) > 0 ||
               checkInDate.getValue().isBefore(LocalDate.now())) {
        searchStatus.setText("Please select valid check-in and check-out dates.");
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

  /**
  * Desc: gets the location from the dashboard searchbar
  * @return: location
  */
  public static String getLocation() {
    return location;
  }

  /**
  * Desc: gets the check-in date the user picked from the calendar
  * @return: userCheckInDate
  */
  static LocalDate getUserCheckInDate() {
    return userCheckInDate;
  }

  /**
  * Desc: gets the check-out date the user picked from the calendar
  * @return: userCheckOutDate
  */
  static LocalDate getUserCheckOutDate() {
    return userCheckOutDate;
  }

  /**
  * Desc: gets the number of rooms the user chose from the dashboard
  * @return: numOfRooms
  */
  static int getNumOfRooms() {
    return numOfRooms;
  }

  /**
  * Desc: sets the number of rooms
  */
  public void modifyRoom() {
    this.roomCount.setValueFactory(roomCountFactory);
  }

  /**
  * Desc: loads the current room count set by the user
  */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    roomCount.setValueFactory(roomCountFactory);
  }
}


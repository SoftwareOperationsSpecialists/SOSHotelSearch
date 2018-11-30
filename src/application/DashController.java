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
import java.util.ResourceBundle;

//functionality for the initial search from the dashboard
public class DashController implements Initializable {

  @FXML
  private TextField searchBar;                // search bar where location is to be entered

  @FXML
  private DatePicker checkInDate;             //check-in date
  @FXML
  private DatePicker checkOutDate;            //check-out date

  @FXML
  private Label searchStatus;                  // notifies the user if they try to search without
                                               // filling in all required fields
  @FXML
  private Spinner roomCount;                   // spinner for number of rooms

  @FXML
  private ProgressIndicator indicator;         // indicates that the search screen is loading

  private static Reservation reservation;      // reservation made by the user
  private static String location;              // location user has searched for


  //allows user to inc/dec # of rooms
  private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0, 9, 1);

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
    location = searchBar.getText(); // location variable becomes what is written in the search bar

    //checks if a location was entered
    if (location.isEmpty()) {
      searchStatus.setText("Must input Location");

      //checks if dates were entered
    } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
      searchStatus.setText("Please select check in and check out date!");

      //all information was entered, so generates the map and goes to the map scene
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
        reservation = new Reservation(checkInDate.getValue(), checkOutDate.getValue(),
                                      (int) roomCount.getValue());

        //checks for error
        if (mapManager.getErrorStatus()) {
          searchStatus.setText(mapManager.getError());
        } else {
          searchStatus.setVisible(false);
          try {
            Navigator.search(event); // go to search screen
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

  //gets location
  public static String getLocation() {
    return location;
  }

  //gets reservation details
  public static Reservation getReservation() {
    return reservation;
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


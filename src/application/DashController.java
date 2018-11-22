package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// Sets up the dashboard where the user can begin their initial search
public class DashController {
  @FXML
  private TextField searchBar;          // TextField where user can type the location
  private static String location;       // desired location of hotel

  @FXML
  private MenuButton navigationBtn;     // button that starts the search

  @FXML
  private MenuItem myAccountItem;       // allows the user to view their account
  @FXML
  private MenuItem mySavedHotelItem;    // allows the user to view their saved hotels
  @FXML
  private MenuItem myReservationItem;   // allows the user to view their reservations
  @FXML
  private MenuItem logoutItem;          // allows the user to log out

  @FXML
  private Spinner roomCount;            // allows the user to toggle the amount of rooms they want
  private SpinnerValueFactory<Integer> roomCountFactory = new IntegerSpinnerValueFactory(0,9,1);


  //Side Panel buttons
  public void MyAccount(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void logout(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }
  
  // renders the search results based on the information given by the user when the search button is clicked
  public void Search(javafx.event.ActionEvent event) throws Exception {
    location = searchBar.getText();               // gets location from the searchBar TextField

    if (!location.isEmpty()) {
      // create map at given location
      MapDriver mapDriver = new MapDriver();      
      mapDriver.setAddress(location);
      mapDriver.createMap();
      
      if (mapDriver.getErrorStatus())   {
        // error: prints error
        System.out.println(mapDriver.getError()); // this should be set to the text of a label
      }
      else {
        // no error: goes to search results
        Parent Dashboard = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene dashboard = new Scene(Dashboard);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboard);
        window.show();
      }
    }
    else {
      // display error message
    }
  }


  public static String getLocation() {
    return location;
  }

  public void modifyRoom() {
    this.roomCount.setValueFactory(roomCountFactory);   // updates new roomCount value
  }

  public void changeScene(){
  }

}

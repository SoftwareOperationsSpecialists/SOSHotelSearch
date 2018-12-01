package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Navigator {

  static void dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(Navigator.class.getResource("Dashboard.fxml"));
    Scene dashboardScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
  }

  static void hotelInfo(Hotel hotel, ActionEvent event) throws Exception {
    HotelController hotelController = new HotelController();
    HotelController.setHotel(hotel);

    Parent HotelInfo = FXMLLoader.load(Navigator.class.getResource("Hotel.fxml"));
    Scene hotelInfoScene = new Scene(HotelInfo);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotelInfoScene);
    window.show();
  }

  static void logout(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(Navigator.class.getResource("login.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }

  static void myAccount(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(Navigator.class.getResource("MyAccount.fxml"));
    Scene myAccountScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(myAccountScene);
    window.show();
  }

  static void payment(ActionEvent event) throws Exception {
    Parent paymentInfo = FXMLLoader.load(Navigator.class.getResource("Payment.fxml"));
    Scene paymentScene = new Scene(paymentInfo);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(paymentScene);
    window.show();
  }

  static void register(ActionEvent event) throws Exception {
    Parent register = FXMLLoader.load(Navigator.class.getResource("Register.fxml"));
    Scene registerScene = new Scene(register);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(registerScene);
    window.show();
  }

  static void reviews(ActionEvent event) throws Exception {
    Parent Reviews = FXMLLoader.load(Navigator.class.getResource("Reviews.fxml"));
    Scene reviews = new Scene(Reviews);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(reviews);
    window.show();
  }

  static void savedHotels(ActionEvent event) throws Exception {
    Parent Saved = FXMLLoader.load(Navigator.class.getResource("SavedHotels.fxml"));
    Scene savedScene = new Scene(Saved);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(savedScene);
    window.show();
  }

  static void search(ActionEvent event) throws Exception {
    Parent Search = FXMLLoader.load(Navigator.class.getResource("Search.fxml"));
    Scene searchScene = new Scene(Search);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(searchScene);
    window.show();
  }

  static void reservation(ActionEvent event) throws Exception {
    Parent Reservations = FXMLLoader.load(Navigator.class.getResource("Reservations.fxml"));
    Scene reservation = new Scene(Reservations);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(reservation);
    window.show();
  }

  static void hotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(Navigator.class.getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }

  static void thankYouScene(ActionEvent event) throws Exception {

    Parent FinalScene = FXMLLoader.load(Navigator.class.getResource("FinalScene.fxml"));
    Scene finalScene = new Scene(FinalScene);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(finalScene);
    window.show();


  }
}

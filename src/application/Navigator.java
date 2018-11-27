package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Navigator {
  public void dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene dashboardScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
  }

  public void hotelInfo(Hotel hotel, ActionEvent event) throws Exception {
    HotelController hotelController = new HotelController();
    hotelController.setHotel(hotel);

    Parent HotelInfo = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotelInfoScene = new Scene(HotelInfo);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotelInfoScene);
    window.show();
  }

  public void logout(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }

  public void myAccount(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
    Scene myAccountScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(myAccountScene);
    window.show();
  }

  public void payment(ActionEvent event) throws Exception {
    Parent paymentInfo = FXMLLoader.load(getClass().getResource("Payment.fxml"));
    Scene paymentScene = new Scene(paymentInfo);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(paymentScene);
    window.show();
  }

  public void register(ActionEvent event) throws Exception {
    Parent register = FXMLLoader.load(getClass().getResource("Register.fxml"));
    Scene registerScene = new Scene(register);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(registerScene);
    window.show();
  }

  public void reviews(ActionEvent event) throws Exception {
    Parent Reviews = FXMLLoader.load(getClass().getResource("Reviews.fxml"));
    Scene reviews = new Scene(Reviews);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(reviews);
    window.show();
  }

  public void savedHotels(ActionEvent event) throws Exception {
    Parent Saved = FXMLLoader.load(getClass().getResource("SavedHotels.fxml"));
    Scene savedScene = new Scene(Saved);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(savedScene);
    window.show();
  }

  public void search(ActionEvent event) throws Exception {
    Parent Search = FXMLLoader.load(getClass().getResource("Search.fxml"));
    Scene searchScene = new Scene(Search);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(searchScene);
    window.show();
  }
}

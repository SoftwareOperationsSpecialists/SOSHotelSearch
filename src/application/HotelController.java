package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HotelController {








  // Book it button will open Payment information Scene
  public void BookItButton(ActionEvent event) throws Exception {

    Parent paymentInfo = FXMLLoader.load(getClass().getResource("Payment.fxml"));
    Scene payment = new Scene(paymentInfo);
    //Goes to payment scene
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(payment);
    window.show();
  }

  // Cancel Button will go back to the "Hotel Search" Scene
  public void CancelButton(ActionEvent event) throws Exception {

    Parent HotelSearch = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene hotelSearch = new Scene(HotelSearch);
    //Goes to hotel search scene
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotelSearch);
    window.show();
  }

  //Go to Reviews button will go to the "Reviews" Scene
  public void GoToReviews(ActionEvent event) throws Exception {

    Parent Reviews = FXMLLoader.load(getClass().getResource("Reviews.fxml"));
    Scene reviews = new Scene(Reviews);
    //Goes to review Scene
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(reviews);
    window.show();
  }


}

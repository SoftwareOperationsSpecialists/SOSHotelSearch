package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class HotelController {

  @FXML
  private ImageView hotelPhotos;

  private int numImages = 2;
  private int imageArrayIndex = 0;
  private ArrayList<Image> images = new ArrayList<>(numImages);

  public HotelController(){
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4629618286-16x5.jpg"));
    images.add(new Image("application/hotelpics/room.jpg"));
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4549822872-4x3.jpg"));
  }


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

  public void NextImage(ActionEvent event) throws Exception {
    try {
      imageArrayIndex++;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(0));
      imageArrayIndex = 0;
    }
  }

  public void PreviousImage(ActionEvent event) throws Exception {
    try {
      imageArrayIndex--;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(images.size()-1));
      imageArrayIndex = images.size()-1;
    }
  }
}
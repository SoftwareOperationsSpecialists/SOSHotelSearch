package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;

//this class is for the hotel information when the user views a specific hotel
public class HotelController {

  @FXML
  private ImageView hotelPhotos;  //photos
  
  @FXML
  private Text hotelName;         //name
  @FXML
  private Text hotelLocation;     //location
  @FXML
  private Text hotelStars;        //stars
  @FXML
  private Text hotelPrice;        //price


  private int numImages = 2;        //there will be three hotel images with indexes 0, 1, and 2
  private int imageArrayIndex = 0;  //sets the image index to 0
  private ArrayList<Image> images = new ArrayList<>(numImages); //array list of the images
  private static Hotel hotel;

  public void initialize(){
    //creates the three images of the hotel
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4629618286-16x5.jpg"));
    images.add(new Image("application/hotelpics/room.jpg"));
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4549822872-4x3.jpg"));

    //initializes hotel info
    hotelName.setText(hotel.getName());                                               //name of hotel
    hotelLocation.setText("Location: "+hotel.getCity()+", "+hotel.getCountryName());  //location: city, country
    hotelStars.setText("This is a "+ Hotel.getStars()+" star hotel.");                //stars
    hotelPrice.setText("Price : $"+hotel.getStdPrice());                              //price
  }
  
  // Dashboard Button will go back to the "Hotel Search" Scene
  public void DashboardButton(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }

  // Book it button will open Payment information Scene
  public void BookItButton(ActionEvent event) throws Exception {
    Reservation reservation = DashController.getReservation();
    reservation.setHotel(hotel);

    Navigator.payment(event);

    //stores check-in and check-out dates in a string
    String insert_reservation ="INSERT INTO RESERVATIONS VALUES(" + reservation.getCheckInDate()
        + "," + reservation.getCheckOutDate() +")";
    
    //stores hotel name, final cost, stars, location, and number of rooms in a string
    String insert_hotel = "INSERT INTO HOTEL VALUES(" + hotelName +","
        + reservation.getFinalCost() +"," + Hotel.getStars() + "," + DashController.getLocation()
        + "," + reservation.getNumOfRooms() +")";

    try (Connection connection = DriverManager.getConnection(Credentials.url);
        Statement statement = connection.createStatement()) {
        statement.executeUpdate(insert_reservation);
      statement.executeUpdate(insert_hotel);
    }
  }

  //Go to Reviews button will go to the "Reviews" Scene
  public void GoToReviews(ActionEvent event) throws Exception {
    Navigator.reviews(event);
  }
  
  //allows user to view the next hotel image
  public void NextImage(){
    try {
      imageArrayIndex++;                                  //increase image index by 1
      hotelPhotos.setImage(images.get(imageArrayIndex));  //set image to its index
    } catch (IndexOutOfBoundsException e) {               //if user increases index past 2
      hotelPhotos.setImage(images.get(0));                //    it wraps back to 0
      imageArrayIndex = 0;
    }
  }
    
  //allows user to view the previous hotel image
  public void PreviousImage(){
    try {
      imageArrayIndex--;                                  //decrease image index by 1
      hotelPhotos.setImage(images.get(imageArrayIndex));  //set image to its index
    } catch (IndexOutOfBoundsException e) {               //if user decreases index below 0
      hotelPhotos.setImage(images.get(images.size()-1));  //  it wraps to the number of images - 1
      imageArrayIndex = images.size()-1;                  //  which brings the index to 2
    }
  }
    
  //sets the hotel to the current hotel
  public static void setHotel(Hotel thisHotel) {
    hotel = thisHotel;
  }
}

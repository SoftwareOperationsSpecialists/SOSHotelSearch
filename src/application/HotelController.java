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

public class HotelController {

  @FXML
  private ImageView hotelPhotos;
  
  @FXML
  private Text hotelName;
  @FXML
  private Text hotelLocation;
  @FXML
  private Text hotelStars;
  @FXML
  private Text hotelPrice;


  private int numImages = 2;
  private int imageArrayIndex = 0;
  private ArrayList<Image> images = new ArrayList<>(numImages);
  private static Navigator navigator = new Navigator();
  private static Hotel hotel;
  static String url = "jdbc:derby:lib/SOSHotelAccountDB";
  static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

  public void initialize(){
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4629618286-16x5.jpg"));
    images.add(new Image("application/hotelpics/room.jpg"));
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4549822872-4x3.jpg"));

    hotelName.setText(hotel.getName());
    hotelLocation.setText("Location: "+hotel.getCity()+", "+hotel.getCountryName());
    hotelStars.setText("This is a "+hotel.getStars()+" star hotel.");
    hotelPrice.setText("Price : "+hotel.getStdPrice()+"$");
  }
  
  // Dashboard Button will go back to the "Hotel Search" Scene
  public void DashboardButton(ActionEvent event) throws Exception {
    navigator.dashboard(event);
  }
  public void logout(ActionEvent event) throws Exception {
    navigator.logout(event);
  }

  // Book it button will open Payment information Scene
  public void BookItButton(ActionEvent event) throws Exception {
    Reservation reservation = new Reservation(hotel, DashController.getUserCheckInDate(),
        DashController.getUserCheckOutDate(), DashController.getNumOfRooms() );

    navigator.payment(event);

    String insert_reservation ="INSERT INTO RESERVATIONS VALUES(" + Reservation.getCheckInDate()
        + "," + Reservation.getCheckOutDate() +")";

        String insert_hotel = "INSERT INTO HOTEL VALUES(" + hotelName +","
            + Reservation.getFinalCost() +","+ hotelStars +","+ DashController.getLocation();


    try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()) {
        statement.executeUpdate(insert_reservation);
      statement.executeUpdate(insert_hotel);


    }

  }

  //Go to Reviews button will go to the "Reviews" Scene
  public void GoToReviews(ActionEvent event) throws Exception {
    navigator.reviews(event);
  }
  
  public void NextImage(){
    try {
      imageArrayIndex++;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(0));
      imageArrayIndex = 0;
    }
  }
    
  public void PreviousImage(){
    try {
      imageArrayIndex--;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(images.size()-1));
      imageArrayIndex = images.size()-1;
    }
  }
    
  public static void setHotel(Hotel thisHotel) {
    hotel = thisHotel;
  }
}

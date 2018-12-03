package application;

import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 *
 */

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
  private static Hotel hotel;
  private static Reservation reservation;
  private int id;
  private String GET_ID = "SELECT USER_ID FROM SOS.SEARCHER WHERE USERNAME ="
      + LogInController.getClientUsername();

  public void initialize(){
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4629618286-16x5.jpg"));
    images.add(new Image("application/hotelpics/room.jpg"));
    images.add(new Image("application/hotelpics/holiday-inn-the-colony-4549822872-4x3.jpg"));

    hotelName.setText(hotel.getName());
    hotelLocation.setText("Location: "+hotel.getCity()+", "+hotel.getCountryName());
    hotelStars.setText("This is a "+ hotel.getStars()+" star hotel.");
    hotelPrice.setText("Price : $"+hotel.getPrice()+"/night");

  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void dashboardButton(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void myAccount(ActionEvent event) throws Exception {
    Navigator.myAccount(event);
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void bookItButton(ActionEvent event) throws Exception {
    try (Connection conn = DriverManager.getConnection(Credentials.getUrl());
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(GET_ID)) {
      resultSet.next();
      id = resultSet.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    reservation = new Reservation(hotel, DashController.getUserCheckInDate(),
        DashController.getUserCheckOutDate(), DashController.getNumOfRooms());

    Navigator.payment(event);

    String checkInDate = reservation.getCheckInDate().toString();
    String checkOutDate = reservation.getCheckOutDate().toString();

    String bookedHotelName = reservation.getHotel().getCity();
    int bookedHotelPrice = reservation.getFinalCost();
    double rating = reservation.getHotel().getPrice();
    String location = DashController.getLocation();
    int rooms = DashController.getNumOfRooms();

    String insert_reservation ="INSERT INTO SOS.RESERVATIONS (CHECKIN, CHECKOUT, HOTEL_ID) VALUES('"+checkInDate+"','"+checkOutDate+"','"+id+"')";

        String insert_hotel = "INSERT INTO SOS.HOTEL (ID, NAME, PRICE, RATING, LOCATION, ROOMS) VALUES('"+id+"','"+bookedHotelName+"',"+bookedHotelPrice+","+rating+",'"+location+"',"+rooms+")";
    try {
      Connection connection = DriverManager.getConnection(Credentials.getUrl());
      PreparedStatement insertReservation = connection.prepareStatement(insert_reservation);
      PreparedStatement insertHotel = connection.prepareStatement(insert_hotel);
      insertReservation.executeUpdate();
      insertHotel.executeUpdate();
      connection.close();
    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  /**
   *
   * @param event
   * @throws Exception
   */
  public void GoToReviews(ActionEvent event) throws Exception {
    Navigator.reviews(event);
  }

  /**
   *
   */
  public void NextImage(){
    try {
      imageArrayIndex++;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(0));
      imageArrayIndex = 0;
    }
  }

  /**
   *
   */
  public void PreviousImage(){
    try {
      imageArrayIndex--;
      hotelPhotos.setImage(images.get(imageArrayIndex));
    } catch (IndexOutOfBoundsException e) {
      hotelPhotos.setImage(images.get(images.size()-1));
      imageArrayIndex = images.size()-1;
    }
  }

  /**
   *
   * @param thisHotel
   */
  public static void setHotel(Hotel thisHotel) {
    hotel = thisHotel;
  }

  /**
   *
   * @return
   */
  public static Hotel getHotel() {
    return hotel;
  }

  /**
   *
   * @param thisReservation
   */
  public static void setReservation(Reservation thisReservation) {
    reservation = thisReservation;
  }

  /**
   *
   * @return
   */
  public static Reservation getReservation() {
    return reservation;
  }
}

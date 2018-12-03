package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PayController {
  @FXML
  private Label numberOfRooms;
  @FXML
  private Label price;
  @FXML
  private Label numberOfNights;

  /**
  * Desc: loads the number of rooms, price, and number of nights.
  */
  public void initialize() {
    numberOfRooms.setText("" + DashController.getNumOfRooms());
    price.setText("$" + HotelController.getReservation().getFinalCost());
    numberOfNights.setText("" + HotelController.getReservation().getNumberOfNights());
  }

  /**
  * Desc: goes to the payment scene.
  * @param: event
  */
  public void pay(ActionEvent event) throws Exception {
    Navigator.thankYouScene(event);
  }

  /**
  * Desc: goes to the hotel info scene
  * @param: event
  */
  public void hotelInfo(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}

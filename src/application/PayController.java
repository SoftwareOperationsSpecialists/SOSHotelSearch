package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//functionality for payment scene
public class PayController {
  @FXML
  private Label numberOfRooms;
  @FXML
  private Label price;
  @FXML
  private Label numberOfNights;

  //go to thank you scene which confirms the reservation
  public void Pay(ActionEvent event) throws Exception {
    Navigator.thankYouScene(event);
  }

  //go to hotel info scene
  public void HotelInfo(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}

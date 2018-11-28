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

  public void Pay(ActionEvent event) throws Exception {
    Navigator.thankYouScene(event);
  }

  public void HotelInfo(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}

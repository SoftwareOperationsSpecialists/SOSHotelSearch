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

  public void pay(ActionEvent event) throws Exception {
    Navigator.thankYouScene(event);
  }

  public void hotelInfo(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}

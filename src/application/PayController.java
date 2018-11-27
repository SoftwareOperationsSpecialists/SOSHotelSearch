package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.soap.Text;
import javafx.scene.control.Label;

public class PayController {
@FXML
private Label numberOfRooms;
@FXML
private Label price;
@FXML
private Label numberOfNights;






  public void Pay(ActionEvent event) throws Exception {

    Parent FinalScene = FXMLLoader.load(getClass().getResource("FinalScene.fxml"));
    Scene finalScene = new Scene(FinalScene);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(finalScene);
    window.show();


  }
  public void HotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }

}

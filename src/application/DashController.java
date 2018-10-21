package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashController {

  public void HotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }
  public void HighToLow(ActionEvent event) throws Exception{



  }
}

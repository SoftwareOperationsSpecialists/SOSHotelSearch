package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ReviewsController {

  public void submitButton(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
  public void backButton(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}

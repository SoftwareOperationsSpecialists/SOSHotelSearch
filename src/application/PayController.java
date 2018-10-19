package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PayController {

  public void Pay(ActionEvent event) throws Exception {

    Parent newScene = FXMLLoader.load(getClass().getResource("FinalScene.fxml"));
    Scene newScene1 = new Scene(newScene);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(newScene1);
    window.show();


  }
}

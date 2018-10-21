package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PayController {

  public void Pay(ActionEvent event) throws Exception {

    Parent FinalScene = FXMLLoader.load(getClass().getResource("FinalScene.fxml"));
    Scene finalScene = new Scene(FinalScene);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(finalScene);
    window.show();


  }
}

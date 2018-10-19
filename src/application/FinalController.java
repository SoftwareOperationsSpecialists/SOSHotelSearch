package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalController {

  public void Ok(ActionEvent event) throws Exception {

    Parent dash = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene dash1 = new Scene(dash);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dash1);
    window.show();


  }

}

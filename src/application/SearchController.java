package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchController {

  public void Search(javafx.event.ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene dashboard = new Scene(Dashboard);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }
}

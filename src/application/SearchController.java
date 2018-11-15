package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchController {
  @FXML
  private TextField searchBar;
  private static String location;

  public void Search(javafx.event.ActionEvent event) throws Exception {
    location = searchBar.getText();

    if (!location.isEmpty()) {
      Parent Dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
      Scene dashboard = new Scene(Dashboard);
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(dashboard);
      window.show();
    }
    else {
      // display error message
    }
  }

  public static String getLocation() {
    return location;
  }
}

package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HotelOwnerController implements Initializable {

  @FXML
  private TableView<Info> tableView;
  @FXML
  private TableColumn<Info, Integer> NameCol;
  @FXML
  private TableColumn<Info, String> CheckInCol;
  @FXML
  private TableColumn<Info, String> CheckOutCol;
  @FXML
  private TableColumn<Info, String> NumRoomsCol;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public static class Info {

    // Class constructor takes in fields as parameters

  }

  public void logout(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }
}

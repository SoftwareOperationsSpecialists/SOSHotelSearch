package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class LogInController {

  @FXML
  private Label lblStatus;

  @FXML
  private TextField txtUsername;
  @FXML
  private TextField txtPassword;

  @FXML
  private RadioButton searcherBtn;
  @FXML
  private RadioButton ownerBtn;

  @FXML
  private ToggleGroup loginType;

  static String url = "jdbc:derby:/Users/trainofthought/Documents/Allen/Application/lib"
      + "/SOSHotelAccountDB";

  //Action for login button
  public void Login(ActionEvent event) throws Exception {

    RadioButton selectedRadioButton = (RadioButton) loginType.getSelectedToggle();

    if (selectedRadioButton == searcherBtn) {
      try {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection loginConnection = DriverManager.getConnection(url);

        String sql = "SELECT USERNAME, PASSWORD FROM SOS.SEARCHER WHERE USERNAME='"
            + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";

        Statement statement = loginConnection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next()) {
          Parent Search = FXMLLoader.load(getClass().getResource("Search.fxml"));
          Scene search = new Scene(Search);

          //Goes to register screen
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(search);
          window.show();
          loginConnection.close();
        } else {
          lblStatus.setText("Login Failed");
          lblStatus.setTextFill(Paint.valueOf("red"));
          loginConnection.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex);

      }
    } else if (selectedRadioButton == ownerBtn) {
      try {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection loginConnection = DriverManager.getConnection(url);

        String sql = "SELECT USERNAME, PASSWORD FROM SOS.OWNER WHERE USERNAME='"
            + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";

        Statement statement = loginConnection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next()) {
          Parent Search = FXMLLoader.load(getClass().getResource("Search.fxml"));
          Scene search = new Scene(Search);

          //Goes to register screen
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
          window.setScene(search);
          window.show();
          loginConnection.close();
        } else {
          lblStatus.setText("Login Failed");
          lblStatus.setTextFill(Paint.valueOf("red"));
          loginConnection.close();
          //To change scenes copy and paste this into if statement and enter the new fxml scene name into parent root
        }
      } catch (SQLException ex) {
        System.out.println(ex);
      }
    }
  }

  //Register button action
  public void SignUp(ActionEvent event) throws Exception {

    Parent register = FXMLLoader.load(getClass().getResource("Register.fxml"));
    Scene registerScene = new Scene(register);

    //Goes to register screen
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(registerScene);
    window.show();
  }

}

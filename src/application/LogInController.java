package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import sun.rmi.runtime.Log;

import java.sql.*;

public class LogInController extends Credentials {

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

  //Action for login button
  public void Login(ActionEvent event) throws Exception {

    RadioButton selectedRadioButton = (RadioButton) loginType.getSelectedToggle();

    if (selectedRadioButton == searcherBtn) {
      try {
        Class.forName(Credentials.getDriver());
        Connection loginConnection = DriverManager.getConnection(Credentials.getUrl());
        Statement statement = loginConnection.createStatement();
        String searcherSQL = "SELECT USERNAME, PASSWORD FROM SOS.SEARCHER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        ResultSet result = statement.executeQuery(searcherSQL);

        if (result.next()) {
          Navigator.dashboard(event);
          clientUsername = txtUsername.getText();
          LogInController.setClientPassword(txtPassword.getText());
          LogInController.setIsSearcher(true);
          loginConnection.close();
          statement.close();
        } else {
          lblStatus.setText("Login Failed");
          lblStatus.setTextFill(Paint.valueOf("red"));
          loginConnection.close();
          statement.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex);

      }
    } else if (selectedRadioButton == ownerBtn) {
      try {
        Class.forName(LogInController.getDriver());
        Connection loginConnection = DriverManager.getConnection(LogInController.getUrl());
        String ownerSQL = "SELECT USERNAME, PASSWORD FROM SOS.OWNER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        Statement statement = loginConnection.createStatement();
        ResultSet result = statement.executeQuery(ownerSQL);

        if (result.next()) {
          Navigator.hotelOwner(event);
          LogInController.setClientUsername(txtUsername.getText());
          LogInController.setClientPassword(txtPassword.getText());
          result.close();
          loginConnection.close();
          statement.close();
        } else {
          lblStatus.setText("Login Failed");
          lblStatus.setTextFill(Paint.valueOf("red"));
          result.close();
          loginConnection.close();
          statement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  //register button action
  public void signUp(ActionEvent event) throws Exception {
    Navigator.register(event);
  }

}

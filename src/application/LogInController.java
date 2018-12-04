package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;

import java.sql.*;

/**
 * Desc: controller for the login screen
 */

public class LogInController extends Credentials {

  @FXML
  private Label lblStatus;

  @FXML
  private TextField txtUsername;
  @FXML
  private TextField txtPassword;

  //radio buttons to select hotel searcher or hotel owner
  @FXML
  private RadioButton searcherBtn;
  @FXML
  private RadioButton ownerBtn;

  @FXML
  private ToggleGroup loginType;

  /**
   * Desc: proceeds to the next screen. Checks user credentials for validity
   * and account type.
   *
   * @param: event - the event handler for the button
   * @throws: Exception
   */
  public void Login(ActionEvent event) throws Exception {

    //gets status of whether the user has chosen hotel searcher or hotel owner
    RadioButton selectedRadioButton = (RadioButton) loginType.getSelectedToggle();

    //hotel searcher login
    if (selectedRadioButton == searcherBtn) {
      //check if login info is correct
      try {
        Class.forName(Credentials.getDriver());
        Connection loginConnection = DriverManager.getConnection(Credentials.getUrl());
        Statement statement = loginConnection.createStatement();
        String searcherSQL = "SELECT USERNAME, PASSWORD FROM SOS.SEARCHER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        ResultSet result = statement.executeQuery(searcherSQL);

        //login successful
        if (result.next()) {
          Navigator.dashboard(event);               //go to dashboard scene
          setClientUsername(txtUsername.getText());
          setClientPassword(txtPassword.getText());
          setIsSearcher(true);      //set as hotel searcher
          loginConnection.close();
          statement.close();
        } else {
          //login failed
          lblStatus.setText("Login Failed");            //write login failed
          lblStatus.setTextFill(Paint.valueOf("red"));  //    in red
          loginConnection.close();
          statement.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex);

      }

      //hotel owner login
    } else if (selectedRadioButton == ownerBtn) {
      //check if login info is correct
      try {
        Class.forName(LogInController.getDriver());
        Connection loginConnection = DriverManager.getConnection(LogInController.getUrl());
        String ownerSQL = "SELECT USERNAME, PASSWORD FROM SOS.OWNER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        Statement statement = loginConnection.createStatement();
        ResultSet result = statement.executeQuery(ownerSQL);

        //login successful
        if (result.next()) {
          Navigator.hotelOwner(event);
          setClientUsername(txtUsername.getText());
          setClientPassword(txtPassword.getText());
          result.close();
          loginConnection.close();
          statement.close();
        } else {
          //login failed
          lblStatus.setText("Login Failed");            //write login failed
          lblStatus.setTextFill(Paint.valueOf("red"));  //    in red
          result.close();
          loginConnection.close();
          statement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Desc: takes user to register screen
   *
   * @param: event - the event handler for the button
   * @throws: Exception
   */
  public void signUp(ActionEvent event) throws Exception {
    Navigator.register(event);
  }
}

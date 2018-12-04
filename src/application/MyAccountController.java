package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Desc: allows the user to edit account information.
 */

public class MyAccountController extends Credentials implements Initializable {

  @FXML
  private TextField txtFullName;
  @FXML
  private TextField txtPassword;
  @FXML
  private TextField txtUserName;
  @FXML
  private TextField txtEmail;
  @FXML
  private TextField txtDOB;

  @FXML
  private Label updateStatus;

  /**
   * Desc: edits the full name, password, email, and date of birth
   * tied to the account.
   */
  public void editInformation() {

    String newName = txtFullName.getText();     //updates new name
    String newPassword = txtPassword.getText(); //updates new password
    String newEmail = txtEmail.getText();       //updates new email
    String newBirthDate = txtDOB.getText();     //updates new date of birth

    //checks the validity of the full name.
    if (validFullNamePattern(txtFullName.getText())) {
      updateStatus.setText("Invalid name input!");
      updateStatus.setTextFill(Paint.valueOf("red"));

      //checks the validity of the password.
    } else if (validPSWDPattern(txtPassword.getText())) {
      updateStatus.setText("Password must not contain special characters!");
      updateStatus.setTextFill(Paint.valueOf("red"));

      //checks the validity of the email address.
    } else if (validEmailPattern(txtEmail.getText())) {
      updateStatus.setText("Must be a valid email address!");
      updateStatus.setTextFill(Paint.valueOf("red"));

      //checks the validity of the date of birth.
    } else if (validDOBPattern(txtDOB.getText())) {
      updateStatus.setText("DOB Pattern: MM/DD/YYYY");
      updateStatus.setTextFill(Paint.valueOf("red"));

    } else {
      try {
        //update username and info
        String username = txtUserName.getText();
        update(newName, newPassword, newEmail, newBirthDate, username,
                LogInController.getUpdateSQL(), updateStatus);

      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
  }

  //Side panel buttons

  /**
   * Desc: goes to the dashboard scene
   *
   * @param: event - the ActionEvent for the button
   * @throws: Exception
   */
  public void dashboard(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }

  /**
   * Desc: goes to the reservations scene
   *
   * @param: event - the ActionEvent for the button
   * @throws: Exception
   */
  public void reservation(ActionEvent event) throws Exception {
    Navigator.reservation(event);
  }

  /**
   * Desc: goes to the login scene
   *
   * @param: event - the ActionEvent for the button
   * @throws: Exception
   */
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }

  /**
   * Desc: loads the text for the username and password.
   *
   * @param: location
   * @param: resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    txtUserName.setText(getClientUsername());
    txtPassword.setText(getClientPassword());
    if (getIsSearcher()) {
      String searcher = "SOS.SEARCHER";
      getAccountData(searcher);
    } else {
      String owner = "SOS.OWNER";
      getAccountData(owner);
    }
  }

  /**
   * Desc: gets the fullname, email, and date of birth
   *
   * @param: accTypeSQL - account type
   */
  private void getAccountData(String accTypeSQL) {
    Statement grabInfo = null;
    String client = getClientUsername();
    try {
      Class.forName(LogInController.getDriver());
      String getInfoSQL = "SELECT NAME, DOB, PASSWORD, EMAIL FROM " + accTypeSQL + " WHERE USERNAME='"
              + client + "'";
      Connection loginConnection = DriverManager.getConnection(LogInController.getUrl());
      grabInfo = loginConnection.createStatement();
      ResultSet result = grabInfo.executeQuery(getInfoSQL);
      if (result.next()) {
        txtFullName.setText(result.getString(1));
        txtEmail.setText(result.getString(4));
        txtDOB.setText(result.getString(2));
        loginConnection.close();
        grabInfo.close();
        result.close();
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}


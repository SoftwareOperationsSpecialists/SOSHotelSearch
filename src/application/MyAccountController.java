package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//functionality for editing account info
public class MyAccountController extends Credentials implements Initializable {
  //initialize TextFields
  @FXML
  private TextField txtFullName;    //full name
  @FXML
  private TextField txtPassword;    //password
  @FXML
  private TextField txtUserName;    //username
  @FXML
  private TextField txtEmail;       //email address
  @FXML
  private TextField txtDOB;         //date of birth

  @FXML
  private Label updateStatus;

  //function that will edit the information
  public void editInformation(){

      String newName = txtFullName.getText();       //update new name  
      String newPassword = txtPassword.getText();   //update new password
      String newEmail = txtEmail.getText();         //update email address
      String newBirthDate = txtDOB.getText();       //update DOB

      //checks if full name is invalid
      if (validFullNamePattern(txtFullName.getText())) {
          updateStatus.setText("Invalid name input!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      //checks if password is invalid
      } else if (validPSWDPattern(txtPassword.getText())) {
          updateStatus.setText("Password must not contain special characters!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      //checks if email is invalid
      }else if (validEmailPattern(txtEmail.getText())) {
          updateStatus.setText("Must be a valid email address!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      //checks if DOB is invalid
      } else if (validDOBPattern(txtDOB.getText())) {
          updateStatus.setText("DOB Pattern: MM/DD/YYYY");
          updateStatus.setTextFill(Paint.valueOf("red"));

      } else {
          try {
              //update username and info
              String username = txtUserName.getText();
              update(newName, newPassword, newEmail, newBirthDate, username, updateSQL, updateStatus);

          } catch (ClassNotFoundException | SQLException e) {
              e.printStackTrace();
          }
      }
  }
  //Side panel buttons
  public void Dashboard(ActionEvent event) throws Exception {
    Navigator.dashboard(event);               //go to dashboard scene
  }
  public void savedHotels(ActionEvent event) throws Exception {
    Navigator.savedHotels(event);             //go to saved hotels scene
  }
  public void reservation(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Reservations.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }
  public void logout(ActionEvent event) throws Exception {
    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUserName.setText(LogInController.clientUsername);
        txtPassword.setText(LogInController.clientPassword);
        try {
            Class.forName(driver);
            String getInfoSQL = "SELECT NAME, DOB, PASSWORD, EMAIL FROM SOS.SEARCHER WHERE USERNAME='"
                    + txtUserName.getText() + "'";
            Connection loginConnection = DriverManager.getConnection(url);
            Statement grabInfo = loginConnection.createStatement();
            ResultSet result = grabInfo.executeQuery(getInfoSQL);
            if (result.next()){
                txtFullName.setText(result.getString(1));
                txtEmail.setText(result.getString(4));
                txtDOB.setText(result.getString(2));
                loginConnection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

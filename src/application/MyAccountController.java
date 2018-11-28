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

public class MyAccountController implements Initializable {

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

  private String updateSQL = "UPDATE SOS.SEARCHER SET NAME = ?, PASSWORD = ?, EMAIL = ?, DOB = ? " +
          "WHERE USERNAME = ?";

    //function that will edit the information
  public void editInformation(){

      String newName = txtFullName.getText();
      String newPassword = txtPassword.getText();
      String newEmail = txtEmail.getText();
      String newBirthDate = txtDOB.getText();

      if (!RegController.validFullNamePattern(txtFullName.getText())) {
          updateStatus.setText("Invalid name input!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      } else if (!RegController.validPSWDPattern(txtPassword.getText())) {
          updateStatus.setText("Password must not contain special characters!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      }else if (!RegController.validEmailPattern(txtEmail.getText())) {
          updateStatus.setText("Must be a valid email address!");
          updateStatus.setTextFill(Paint.valueOf("red"));

      } else if (!RegController.validDOBPattern(txtDOB.getText())) {
          updateStatus.setText("DOB Pattern: MM/DD/YYYY");
          updateStatus.setTextFill(Paint.valueOf("red"));

      } else {
          try {
              Class.forName(LogInController.driver);
              Connection loginConnection = DriverManager.getConnection(LogInController.url);
              PreparedStatement update = loginConnection.prepareStatement(updateSQL);

              update.setString(1, newName);
              update.setString(2, newPassword);
              update.setString(3, newEmail);
              update.setString(4, newBirthDate);
              update.setString(5, txtUserName.getText());
              update.executeUpdate();
              updateStatus.setTextFill(Paint.valueOf("green"));
              update.close();

          } catch (ClassNotFoundException | SQLException e) {
              e.printStackTrace();
          }
      }
  }
  //Side panel buttons
  public void Dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void savedHotels(ActionEvent event) throws Exception {
    Parent Saved = FXMLLoader.load(getClass().getResource("SavedHotels.fxml"));
    Scene savedScene = new Scene(Saved);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(savedScene);
    window.show();
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
            Class.forName(LogInController.driver);
            String getInfoSQL = "SELECT NAME, DOB, PASSWORD, EMAIL FROM SOS.SEARCHER WHERE USERNAME='"
                    + txtUserName.getText() + "'";
            Connection loginConnection = DriverManager.getConnection(LogInController.url);
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

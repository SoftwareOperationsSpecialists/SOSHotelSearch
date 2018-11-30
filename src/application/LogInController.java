package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;

import java.sql.*;

//verifies username and password and allows the user to login as a hotel searcher or hotel owner
public class LogInController extends Credentials {
  //initialization 
  @FXML
  private Label lblStatus;    //writes if login fails

  //username and password
  @FXML
  private TextField txtUsername;
  @FXML
  private TextField txtPassword;

  //radio buttons to choose hotel searcher or hotel owner
  @FXML
  private RadioButton searcherBtn;
  @FXML
  private RadioButton ownerBtn;

  @FXML
  private ToggleGroup loginType;  

  //Action for login button
  public void Login(ActionEvent event) throws Exception {

    //gets status of whether the user has chosen hotel searcher or hotel owner
    RadioButton selectedRadioButton = (RadioButton) loginType.getSelectedToggle();

    //hotel searcher login
    if (selectedRadioButton == searcherBtn) {
      try {
        Class.forName(driver);
        Connection loginConnection = DriverManager.getConnection(url);
        Statement statement = loginConnection.createStatement();
        String searcherSQL = "SELECT USERNAME, PASSWORD FROM SOS.SEARCHER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        ResultSet result = statement.executeQuery(searcherSQL);

        //login successful
        if (result.next()) {                                
          Navigator.dashboard(event);                     //go to dashboard scene
          clientUsername = txtUsername.getText();         //gets username
          clientPassword = txtPassword.getText();         //gets password
          loginConnection.close();                        
        } else {                                           
          //login failed
          lblStatus.setText("Login Failed");              //write login failed
          lblStatus.setTextFill(Paint.valueOf("red"));    //  in red
          loginConnection.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex);

      }
      
    //hotel owner login
    } else if (selectedRadioButton == ownerBtn) {
      try {
        Class.forName(driver);
        Connection loginConnection = DriverManager.getConnection(url);
        String ownerSQL = "SELECT USERNAME, PASSWORD FROM SOS.OWNER WHERE USERNAME='"
                + txtUsername.getText() + "'" + " AND PASSWORD='" + txtPassword.getText() + "'";
        Statement statement = loginConnection.createStatement();
        ResultSet result = statement.executeQuery(ownerSQL);

        //login successful
        if (result.next()) {
          Navigator.dashboard(event);                     //go to dashboard scene
          loginConnection.close();
        //login failed
        } else {
          lblStatus.setText("Login Failed");              //write login failed
          lblStatus.setTextFill(Paint.valueOf("red"));    //  in red
          loginConnection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  //Register button action
  public void SignUp(ActionEvent event) throws Exception {
    Navigator.register(event);    //go to create account scene
  }

}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

  @FXML
  private Label lblStatus;


  @FXML
  private TextField txtUsername;

  @FXML
  private TextField txtPassword;

  //Action for login button
  public void Login(ActionEvent event) throws Exception{
    if(txtUsername.getText().equals("User") && txtPassword.getText().equals("pass")){
      lblStatus.setText("Login Success");

      //To change scenes copy and paste this into if statement and enter the new fxml scene name into parent root
      Stage primaryStage = new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml")); //If login is success, Dashboard.fxml will be shown
      primaryStage.setTitle("SOS Hotel Search");
      primaryStage.setScene(new Scene(root, 300, 275));
      primaryStage.show();
    }
    else {

     lblStatus.setText("Login Failed");

    }
  }
  //Register button action
  public void Register(ActionEvent event) throws Exception{

    Stage primaryStage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("Register.fxml")); //Goes to register screen
    primaryStage.setTitle("SOS Hotel Search");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();

  }



}

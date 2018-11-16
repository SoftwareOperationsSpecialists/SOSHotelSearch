package application;

import com.sun.deploy.xml.XMLable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInController {

  @FXML
  private Label lblStatus;

  @FXML
  private TextField txtUsername;

  @FXML
  private TextField txtPassword;

  //Action for login button
  public void Login(ActionEvent event) throws Exception{
    if(txtUsername.getText().equals("") && txtPassword.getText().equals("")){
      lblStatus.setText("Login Success");

      //To change scenes copy and paste this into if statement and enter the new fxml scene name into parent root
      Parent Search = FXMLLoader.load(getClass().getResource("Search.fxml"));
      Scene search = new Scene(Search);
      //Goes to register screen
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(search);
      window.show();
    }
    else {

     lblStatus.setText("Login Failed");
    }
  }
  //Register button action
  public void Register(ActionEvent event) throws Exception{

    Parent register = FXMLLoader.load(getClass().getResource("Register.fxml"));
    Scene registerScene = new Scene(register);
     //Goes to register screen
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
   window.setScene(registerScene);
    window.show();

  }



}

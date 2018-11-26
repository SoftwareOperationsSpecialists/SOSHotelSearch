package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class RegController {

  @FXML
  private Label nameStatus;
  @FXML
  private Label userStatus;
  @FXML
  private Label passwordStatus;
  @FXML
  private Label emailStatus;
  @FXML
  private Label dobStatus;

  @FXML
  private TextField txtFullName;
  @FXML
  private TextField txtUserName;
  @FXML
  private TextField txtEmail;
  @FXML
  private TextField txtDOB;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private RadioButton regSearcherBtn;
  @FXML
  private RadioButton regOwnerBtn;

  @FXML
  private ToggleGroup registerType;

   static String passwordREGEX = "^([0-9A-Za-z@.]{1,255})$";
   static String fullNameREGEX = "[a-zA-Z]*( [a-zA-Z]*)?";
   static String userNameREGEX = "^([a-zA-Z])[a-zA-Z_-]*[\\w_-]*[\\S]$|^([a-zA-Z])"
      + "[0-9_-]*[\\S]$|^[a-zA-Z]*[\\S]$";
   static String emailREGEX = "^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])"
      + "|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$";
   static String dobREGEX = "^(([1-9])|(0[1-9])|(1[0-2]))\\/(([0-9])|([0-2][0-9])|(3[0-1]))" +
           "\\/(([0-9][0-9])|([1-2][0,9][0-9][0-9]))$";


   static Pattern passwordPattern = Pattern.compile(passwordREGEX);
   static Pattern fullNamePattern = Pattern.compile(fullNameREGEX);
   static Pattern userNamePattern = Pattern.compile(userNameREGEX);
   static Pattern emailPattern = Pattern.compile(emailREGEX);
   static Pattern dobPattern = Pattern.compile(dobREGEX);

   private String searcherSql = "INSERT INTO SOS.SEARCHER VALUES(?,?,?,?,?)";
   private String ownerSql = "INSERT INTO SOS.OWNER VALUES(?,?,?,?,?)";

  public void Register(ActionEvent event) throws Exception {
    createHotelSearcher(event);
  }

   static boolean validPSWDPattern(String password) {
    return passwordPattern.matcher(password).matches();
  }

   static boolean validFullNamePattern(String fullName) {
    return fullNamePattern.matcher(fullName).matches();
  }

   static boolean validUserNamePattern(String userName) {
    return userNamePattern.matcher(userName).matches();
  }

   static boolean validEmailPattern(String email) {
    return emailPattern.matcher(email).matches();
  }

   static boolean validDOBPattern(String dob) {
    return dobPattern.matcher(dob).matches();
  }

  public void createHotelSearcher(ActionEvent event) throws IOException, SQLException {

    String name = txtFullName.getText();
    String user = txtUserName.getText();
    String pass = txtPassword.getText();
    String email = txtEmail.getText();
    String birthDate = txtDOB.getText();

    RadioButton selectedRadioButton = (RadioButton) registerType.getSelectedToggle();

    if (!validFullNamePattern(txtFullName.getText())) {
      nameStatus.setText("Invalid name input!");
      nameStatus.setTextFill(Paint.valueOf("red"));

    } else if (!validPSWDPattern(txtPassword.getText())) {
      passwordStatus.setText("Password must not contain special characters!");
      passwordStatus.setTextFill(Paint.valueOf("red"));

    } else if (!validUserNamePattern(txtUserName.getText())) {
      userStatus.setText("Username must not contain spaces!");
      userStatus.setTextFill(Paint.valueOf("red"));

    } else if (!validEmailPattern(txtEmail.getText())) {
      emailStatus.setText("Must be a valid email address!");
      emailStatus.setTextFill(Paint.valueOf("red"));

    } else if (!validDOBPattern(txtDOB.getText())) {
      dobStatus.setText("DOB Pattern: MM/DD/YYYY");
      dobStatus.setTextFill(Paint.valueOf("red"));

    } else if (selectedRadioButton == regSearcherBtn) {
      try {
        Class.forName(LogInController.driver);
        Connection loginConnection = DriverManager.getConnection(LogInController.url);
        PreparedStatement searcherREG = loginConnection.prepareStatement(searcherSql);

        searcherREG.setString(1, user);
        searcherREG.setString(2, birthDate);
        searcherREG.setString(3, pass);
        searcherREG.setString(4, name);
        searcherREG.setString(5, email);

        searcherREG.executeUpdate();
        searcherREG.close();

        Login(event);

      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    } else if (selectedRadioButton == regOwnerBtn) {
      try {
        Class.forName(LogInController.driver);
        Connection loginConnection = DriverManager.getConnection(LogInController.url);
        PreparedStatement ownerREG = loginConnection.prepareStatement(ownerSql);

        ownerREG.setString(1, name);
        ownerREG.setString(2, birthDate);
        ownerREG.setString(3, user);
        ownerREG.setString(4, email);
        ownerREG.setString(5, pass);

        ownerREG.executeUpdate();
        ownerREG.close();

        Login(event);

      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void Login(ActionEvent event) throws IOException {

    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }
}

package application;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.sql.*;
import java.util.regex.Pattern;

abstract class Credentials {

    private String passwordREGEX = "^([0-9A-Za-z@.]{1,255})$";
    private String fullNameREGEX = "[a-zA-Z]*( [a-zA-Z]*)?";
    private String userNameREGEX = "^([a-zA-Z])[a-zA-Z_-]*[\\w_-]*[\\S]$|^([a-zA-Z])"
            + "[0-9_-]*[\\S]$|^[a-zA-Z]*[\\S]$";
    private String emailREGEX = "^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])"
            + "|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$";
    private String dobREGEX = "^(([1-9])|(0[1-9])|(1[0-2]))\\/(([0-9])|([0-2][0-9])|(3[0-1]))" +
            "\\/(([0-9][0-9])|([1-2][0,9][0-9][0-9]))$";

    String searcherSql = "INSERT INTO SOS.SEARCHER VALUES(?,?,?,?,?)";
    String ownerSql = "INSERT INTO SOS.OWNER VALUES(?,?,?,?,?)";
    String updateSQL = "UPDATE SOS.SEARCHER SET NAME = ?, PASSWORD = ?, EMAIL = ?, DOB = ? " +
            "WHERE USERNAME = ?";

    static String url = "jdbc:derby:lib/SOSHotelAccountDB";
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    static String clientUsername;
    static String clientPassword;

    private Pattern passwordPattern = Pattern.compile(passwordREGEX);
    private Pattern fullNamePattern = Pattern.compile(fullNameREGEX);
    private Pattern userNamePattern = Pattern.compile(userNameREGEX);
    private Pattern emailPattern = Pattern.compile(emailREGEX);
    private Pattern dobPattern = Pattern.compile(dobREGEX);

    boolean validPSWDPattern(String password) {
        return !passwordPattern.matcher(password).matches();
    }

    boolean validFullNamePattern(String fullName) {
        return !fullNamePattern.matcher(fullName).matches();
    }

    boolean validUserNamePattern(String userName) {
        return userNamePattern.matcher(userName).matches();
    }

    boolean validEmailPattern(String email) {
        return !emailPattern.matcher(email).matches();
    }

    boolean validDOBPattern(String dob) {
        return !dobPattern.matcher(dob).matches();
    }

    void register(String user, String birthDate, String pass, String name, String email, String typeSQL) throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        Connection loginConnection = DriverManager.getConnection(url);
        PreparedStatement registerStatement = loginConnection.prepareStatement(typeSQL);

        registerStatement.setString(1, user);
        registerStatement.setString(2, birthDate);
        registerStatement.setString(3, pass);
        registerStatement.setString(4, name);
        registerStatement.setString(5, email);

        registerStatement.executeUpdate();
        registerStatement.close();
    }

    void update(String newName, String newPassword, String newEmail, String newBirthDate, String userName, String typeSQL, Label updateStatus) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        Connection loginConnection = DriverManager.getConnection(url);
        PreparedStatement update = loginConnection.prepareStatement(typeSQL);

        update.setString(1, newName);
        update.setString(2, newPassword);
        update.setString(3, newEmail);
        update.setString(4, newBirthDate);
        update.setString(5, userName);
        update.executeUpdate();
        updateStatus.setTextFill(Paint.valueOf("green"));
        update.close();
    }
}

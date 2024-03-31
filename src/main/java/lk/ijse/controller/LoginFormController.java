package lk.ijse.controller;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        String query = "SELECT * FROM userInfo WHERE username = ? AND password = ?";

        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LNVCompany",
                "root",
                "Ijse@123"
        )) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                showAlert("Login Successful", "Welcome, " + username + "!");
            } else {
                showAlert("Login Failed", "Invalid username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while attempting to login.");
        }
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
    }


    public void txtUsernameOnAction(ActionEvent actionEvent) {
    }
    public void lnkLinkOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
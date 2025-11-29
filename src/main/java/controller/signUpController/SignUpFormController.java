package controller.signUpController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.UserController;

import java.io.IOException;

public class SignUpFormController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    UserController userService = new UserController();

    @FXML
    public void btnSignUp(ActionEvent event) {
        String name = txtName.getText().trim();
        String email = txtMail.getText().trim();
        String password = txtPassword.getText().trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill all fields!");
            return;
        }

        UserDTO existingUser = userService.login(name, email, password);
        if(existingUser != null) {
            showAlert(Alert.AlertType.ERROR, "User already exists with this email!");
            return;
        }

        boolean success = userService.register(new UserDTO(name, email, password));
        if(success) {
            showAlert(Alert.AlertType.INFORMATION, "SignUp successful! You can now login.");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "SignUp failed. Please try again.");
        }
    }

    public void btnBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SecurityPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("SignUp Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void clearFields() {
        txtName.clear();
        txtMail.clear();
        txtPassword.clear();
    }
}

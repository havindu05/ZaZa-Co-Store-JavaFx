package controller.loginPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.dto.UserDTO;
import service.UserService;
import service.UserController;

public class LoginPageController {

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    UserService userService = new UserController();

    @FXML
    void btnLogin(ActionEvent event) {

        String email = txtMail.getText().trim();
        String name = txtName.getText().trim();
        String password = txtPassword.getText().trim();

        if(email.isEmpty() || password.isEmpty() || name.isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Please fill all fields!");
            return;
        }

        UserDTO user = userService.login(email, password);

        if(user != null) {
            if(user.getName().equals(name)) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful! Welcome " + user.getName());
            } else {
                showAlert(Alert.AlertType.ERROR, "Name does not match your account!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid email or password. Please try again.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Login Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}

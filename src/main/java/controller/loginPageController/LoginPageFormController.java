package controller.loginPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageFormController {

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtMail1;

    @FXML
    private TextField txtPassword;

    private void openPage(ActionEvent e, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        openPage(event, "/view/SecurityPage.fxml");
    }

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        openPage(event, "/view/DashBoard.fxml");
    }

}

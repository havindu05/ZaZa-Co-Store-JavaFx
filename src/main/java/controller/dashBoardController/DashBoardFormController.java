package controller.dashBoardController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private PieChart userChart;

    @FXML
    void btnCustomer(ActionEvent event) {

    }

    @FXML
    void btnDashBoard(ActionEvent event) {

    }

    @FXML
    void btnEmployee(ActionEvent event) {

    }

    @FXML
    void btnLogOut(ActionEvent event) {

    }

    @FXML
    void btnOrders(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrder.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnProduct(ActionEvent event) {

    }

    @FXML
    void btnReturn(ActionEvent event) {

    }

    @FXML
    void btnSupplier(ActionEvent event) {

    }

    @FXML
    void btnUserChart(MouseEvent event) {

    }

}

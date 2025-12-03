package controller.placeOrderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderTopsController {

    @FXML
    void btnAddToCart(ActionEvent event) {

    }

    @FXML
    void btnBuyNow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);


            Stage stage = new Stage();
            stage.setTitle("Enter Customer Details");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    void btnFrocks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderFrocks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnLogOut(ActionEvent event) {

    }

    @FXML
    void btnOrders(ActionEvent event) {

    }

    @FXML
    void btnPants(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderPants.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {

    }

    @FXML
    void btnProduct(ActionEvent event) {

    }

    @FXML
    void btnReturn(ActionEvent event) {

    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    @FXML
    void btnSkirts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderSkirts.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSupplier(ActionEvent event) {

    }

    @FXML
    void btnTops(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderTops.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnTshirts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderTshirts.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

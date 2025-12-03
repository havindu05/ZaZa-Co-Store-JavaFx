package controller.placeOrderController;

import controller.customerController.CustomerFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class PlaceOrderPantsController {

    @FXML void btnPrettyGirlTees(ActionEvent event)      {
        buyNow("Pretty Girl Tees", 5300.00);
    }

    @FXML void btnDaisyTees(ActionEvent event)           {
        buyNow("Daisy Tees", 5500.00);
    }

    @FXML void btnGirlStyleTrousers(ActionEvent event)   {
        buyNow("GirlStyle Trousers", 9700.00);
    }

    @FXML void btnGirlFitTrousers(ActionEvent event)     {
        buyNow("GirlFit Trousers", 8000.00);
    }

    @FXML void btnClassicGirlPants(ActionEvent event)    {
        buyNow("Classic Girl Pants", 7000.00);
    }

    @FXML void btnSoftCurveTrousers(ActionEvent event)   {
        buyNow("SoftCurve Trousers", 9500.00);
    }


    @FXML void btnAddToCart(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Item added to cart!").show();
    }

    private void buyNow(String itemName, double price) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer.fxml"));
            Parent root = loader.load();

            CustomerFormController controller = loader.getController();
            controller.setProductPrice(price);

            controller.setOnCustomerSaved(() -> {
                String name = controller.getCustomerName();
                int qty = controller.getQuantity();
                double total = qty * price;

                new Alert(Alert.AlertType.INFORMATION) {{
                    setTitle("Order Confirmed!");
                    setHeaderText("Thank You " + name + "!");
                    setContentText(
                            "Item: " + itemName + "\n" +
                                    "Quantity: " + qty + "\n" +
                                    "Total: Rs. " + String.format("%,.2f", total) + "\n\n" +
                                    "Come again soon!"
                    );
                }}.showAndWait();
            });

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Buy - " + itemName);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Cannot open customer form!").show();
        }
    }

    private void loadScene(ActionEvent event, String path) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML void btnDashBoard(ActionEvent e) throws IOException {
        loadScene(e, "/view/DashBoard.fxml");
    }

    @FXML void btnPlaceOrder(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnLogOut(ActionEvent e) throws IOException {
        loadScene(e, "/view/SecurityPage.fxml");
    }


    @FXML void btnFrocks(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnTops(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderTops.fxml");
    }

    @FXML void btnSkirts(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderSkirts.fxml");
    }

    @FXML void btnPants(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderPants.fxml");
    }

    @FXML void btnTshirts(ActionEvent e) throws IOException {
        loadScene(e, "/view/PlaceOrderTshirts.fxml");
    }

    @FXML void btnEmployee(ActionEvent e) {

    }

    @FXML void btnCustomer(ActionEvent e) {

    }

    @FXML void btnProduct(ActionEvent e) {

    }

    @FXML void btnReturn(ActionEvent e) {

    }

    @FXML void btnSupplier(ActionEvent e) {

    }

    @FXML void btnOrders(ActionEvent e) {

    }

    @FXML void btnSearch(ActionEvent e) {

    }


}
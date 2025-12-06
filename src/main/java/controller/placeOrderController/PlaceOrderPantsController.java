package controller.placeOrderController;

import controller.customerController.CustomerFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dto.CartItemDTO;
import service.CartController;

import java.io.IOException;

public class PlaceOrderPantsController {

    @FXML private TextField txtQty;

    private final CartController cartService = CartController.getInstance();

    private String currentItemName = "";
    private double currentItemPrice = 0;

    @FXML
    void btnAddToCart(ActionEvent event) {
        int qty = 1;
        try {
            if (txtQty != null && !txtQty.getText().trim().isEmpty()) {
                qty = Integer.parseInt(txtQty.getText().trim());
                if (qty <= 0) throw new Exception();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid quantity!").show();
            return;
        }

        if (currentItemName.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an item first!").show();
            return;
        }

        CartItemDTO item = new CartItemDTO(
                "I" + System.nanoTime(),
                currentItemName,
                currentItemPrice,
                qty
        );

        cartService.addItem(item);

        new Alert(Alert.AlertType.INFORMATION,
                currentItemName + " × " + qty + " added to cart!\n" +
                        "Cart Total: Rs. " + String.format("%,.2f", cartService.getTotalAmount())
        ).show();

        if (txtQty != null) txtQty.clear();
    }

    private void buyNow(String itemName, double price) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer.fxml"));
            Parent root = loader.load();

            CustomerFormController controller = loader.getController();
            controller.setProductPrice(price);

            controller.setOnCustomerSaved(() -> {
                String customerName = controller.getCustomerName();
                int qty = controller.getQuantity();
                double total = qty * price;

                CartItemDTO item = new CartItemDTO(
                        "I" + System.nanoTime(),
                        itemName,
                        price,
                        qty
                );
                cartService.addItem(item);

                new Alert(Alert.AlertType.INFORMATION) {{
                    setTitle("Order Confirmed!");
                    setHeaderText("Thank You " + customerName + "!");
                    setContentText(
                            "Item: " + itemName + "\n" +
                                    "Quantity: " + qty + "\n" +
                                    "Total: Rs. " + String.format("%,.2f", total) + "\n\n" +
                                    "Come again soon! ♡"
                    );
                }}.showAndWait();
            });

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Buy Now - " + itemName);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Cannot open customer form!").show();
        }
    }

    @FXML void btnPrettyGirlTees(ActionEvent e)      {
        selectItem("Pretty Girl Tees", 5300.00);
        buyNow("Pretty Girl Tees", 5300.00);
    }

    @FXML void btnDaisyTees(ActionEvent e)           {
        selectItem("Daisy Tees", 5500.00);
        buyNow("Daisy Tees", 5500.00);
    }

    @FXML void btnGirlStyleTrousers(ActionEvent e)   {
        selectItem("GirlStyle Trousers", 9700.00);
        buyNow("GirlStyle Trousers", 9700.00);
    }

    @FXML void btnGirlFitTrousers(ActionEvent e)     {
        selectItem("GirlFit Trousers", 8000.00);
        buyNow("GirlFit Trousers", 8000.00);
    }

    @FXML void btnClassicGirlPants(ActionEvent e)    {
        selectItem("Classic Girl Pants", 7000.00);
        buyNow("Classic Girl Pants", 7000.00);
    }

    @FXML void btnSoftCurveTrousers(ActionEvent e)   {
        selectItem("SoftCurve Trousers", 9500.00);
        buyNow("SoftCurve Trousers", 9500.00);
    }


    private void selectItem(String name, double price) {
        currentItemName = name;
        currentItemPrice = price;
    }

    private void loadScene(ActionEvent e, String path) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML void btnMyCart(ActionEvent e) throws IOException    {
        loadScene(e, "/view/CartPage.fxml");
    }

    @FXML void btnDashBoard(ActionEvent e) throws IOException    {
        loadScene(e, "/view/DashBoard.fxml");
    }

    @FXML void btnPlaceOrder(ActionEvent e) throws IOException   {
        loadScene(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnLogOut(ActionEvent e) throws IOException       {
        loadScene(e, "/view/SecurityPage.fxml");
    }

    @FXML void btnTops(ActionEvent e) throws IOException         {
        loadScene(e, "/view/PlaceOrderTops.fxml");
    }

    @FXML void btnSkirts(ActionEvent e) throws IOException       {
        loadScene(e, "/view/PlaceOrderSkirts.fxml");
    }

    @FXML void btnPants(ActionEvent e) throws IOException        {
        loadScene(e, "/view/PlaceOrderPants.fxml");
    }

    @FXML void btnTshirts(ActionEvent e) throws IOException      {
        loadScene(e, "/view/PlaceOrderTshirts.fxml");
    }

    @FXML void btnEmployee(ActionEvent e) throws IOException     {
        loadScene(e, "/view/EmployeeForm.fxml");
    }

    @FXML void btnCustomer(ActionEvent e) throws IOException     {
        loadScene(e, "/view/CustomerForm.fxml");
    }

    @FXML void btnProduct(ActionEvent e) throws IOException      {
        loadScene(e, "/view/ProductForm.fxml");
    }

    @FXML void btnSupplier(ActionEvent e) throws IOException     {
        loadScene(e, "/view/SupplierForm.fxml");
    }

    @FXML void btnOrders(ActionEvent e) throws IOException      {
        loadScene(e, "/view/OrderHistory.fxml");
    }

    public void btnSearch(ActionEvent event) {
    }

    public void btnFrocks(ActionEvent event) {
    }
}
package controller.dashBoardController;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.dto.CartItem;
import service.CartController;

import java.io.IOException;

public class DashBoardFormController {

    @FXML private Label lblTodaySales;
    @FXML private Label lblTotalOrders;
    @FXML private Label lblTotalCustomers;
    @FXML private Label lblCartItems;
    @FXML private PieChart pieChart;

    private final CartController cartService = CartController.getInstance();

    @FXML
    private void initialize() {
        updateCartCount();
        cartService.getAllItems().addListener((ListChangeListener<? super CartItem>) (observable) -> updateCartCount());

        lblTodaySales.setText("Rs. 485,900.00");
        lblTotalOrders.setText("89 Orders");
        lblTotalCustomers.setText("1,247 Customers");

        pieChart.setData(javafx.collections.FXCollections.observableArrayList(
                new PieChart.Data("Frocks", 42),
                new PieChart.Data("Tops", 28),
                new PieChart.Data("Skirts", 15),
                new PieChart.Data("Pants", 10),
                new PieChart.Data("T-Shirts", 5)
        ));
        pieChart.setTitle("Today's Sales");
    }

    private void updateCartCount() {
        int count = cartService.getItemCount();
        lblCartItems.setText(count + " Item" + (count == 1 ? "" : "s") + " in Cart");

        if (count > 0) {
            lblCartItems.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 12 30; -fx-background-radius: 30; -fx-font-size: 18; -fx-font-weight: bold;");
        } else {
            lblCartItems.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-padding: 12 30; -fx-background-radius: 30; -fx-font-size: 18; -fx-font-weight: bold;");
        }
    }


    @FXML void btnDashBoard(ActionEvent e) throws IOException    {
        openPage(e, "/view/DashBoard.fxml");
    }

    @FXML void btnPlaceOrder(ActionEvent e) throws IOException  {
        openPage(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnMyCart(ActionEvent e) throws IOException       {
        openPage(e, "/view/CartPage.fxml");
    }

    @FXML void btnCustomer(ActionEvent e) throws IOException    {
        openPage(e, "/view/CustomerForm.fxml");
    }

    @FXML void btnProduct(ActionEvent e) throws IOException     {
        openPage(e, "/view/ProductForm.fxml");
    }

    @FXML void btnEmployee(ActionEvent e) throws IOException    {
        openPage(e, "/view/EmployeeForm.fxml");
    }

    @FXML void btnSupplier(ActionEvent e) throws IOException    {
        openPage(e, "/view/SupplierForm.fxml");
    }

    @FXML void btnOrders(ActionEvent e) throws IOException      {
        openPage(e, "/view/OrderHistory.fxml");
    }


    @FXML
    void btnLogOut(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SecurityPage.fxml"))));
        stage.setTitle("ZaZa Co. - Login");
        stage.centerOnScreen();
        stage.show();
    }

    private void openPage(ActionEvent e, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.centerOnScreen();
        stage.show();
    }
}
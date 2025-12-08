package controller.cartController;

import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.dto.CartItemDTO;
import service.CartController;

import java.io.IOException;

public class CartFormController {

    @FXML private TableView<CartItemDTO> tblMyCart;
    @FXML private TableColumn<CartItemDTO, String> colItemCode;
    @FXML private TableColumn<CartItemDTO, String> colItem;
    @FXML private TableColumn<CartItemDTO, Double> colPrice;
    @FXML private TableColumn<CartItemDTO, Integer> colQty;
    @FXML private TableColumn<CartItemDTO, Double> colTotal;
    @FXML private TextField txtGrandPrice;

    private final CartController cartService = CartController.getInstance();

    @FXML
    private void initialize() {
        colItemCode.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getItemId()));
        colItem.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colPrice.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPrice()).asObject());
        colQty.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQty()).asObject());
        colTotal.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getTotal()).asObject());

        tblMyCart.setItems(cartService.getAllItems());

        TableColumn<CartItemDTO, Void> actionCol = (TableColumn<CartItemDTO, Void>) tblMyCart.getColumns().get(5);
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button minus = new Button("-");
            private final Label qtyLabel = new Label();
            private final Button plus = new Button("+");
            private final Button remove = new Button("Remove");
            private final HBox box = new HBox(10, minus, qtyLabel, plus, remove);

            {
                minus.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 15; -fx-pref-width: 35;");
                plus.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-background-radius: 15; -fx-pref-width: 35;");
                remove.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 5 15;");
                qtyLabel.setStyle("-fx-background-color: white; -fx-padding: 5 15; -fx-background-radius: 10; -fx-font-weight: bold;");
                box.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                    return;
                }

                CartItemDTO cartItem = getTableRow().getItem();
                qtyLabel.setText(String.valueOf(cartItem.getQty()));

                minus.setOnAction(e -> {
                    if (cartItem.getQty() > 1) {
                        cartService.updateItemQuantity(cartItem, cartItem.getQty() - 1);
                    }
                });
                plus.setOnAction(e -> cartService.updateItemQuantity(cartItem, cartItem.getQty() + 1));
                remove.setOnAction(e -> cartService.removeItem(cartItem));

                setGraphic(box);
            }
        });

        cartService.getAllItems().addListener((ListChangeListener<CartItemDTO>) c -> updateTotal());
        updateTotal();
    }

    private void updateTotal() {
        double total = cartService.getTotalAmount();
        txtGrandPrice.setText(String.format("Rs. %, .2f", total));
    }

    @FXML
    private void btnCheckOut(ActionEvent e) {
        if (cartService.getAllItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Your cart is empty!").show();
            return;
        }

        String orderId = "ZA" + System.currentTimeMillis();
        boolean saved = cartService.saveToDatabase(orderId);

        new Alert(Alert.AlertType.INFORMATION, """
            ORDER SUCCESSFUL!
            
            Order ID: %s
            Total Amount: Rs. %, .2f
            Items: %d
            
            Thank you for shopping at ZaZa Co.
            Your Style, Your Rules ♡
            """.formatted(orderId, cartService.getTotalAmount(), cartService.getItemCount())).show();

        cartService.clearCart();
    }

    private void goTo(ActionEvent e, String fxml) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxml))));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML void btnDashBoard(ActionEvent e) throws IOException { goTo(e,
            "/view/DashBoard.fxml");
    }

    @FXML void btnPlaceOrder(ActionEvent e) throws IOException {
        goTo(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnEmployee(ActionEvent e) throws IOException {
        goTo(e, "/view/Employee.fxml");
    }

    @FXML void btnCustomer(ActionEvent e) throws IOException {
        goTo(e, "/view/CustomerForm.fxml");
    }

    @FXML void btnProduct(ActionEvent e) throws IOException {
        goTo(e, "/view/ProductForm.fxml");
    }

    @FXML void btnSupplier(ActionEvent e) throws IOException {
        goTo(e, "/view/SupplierForm.fxml");
    }

    @FXML void btnOrders(ActionEvent e) throws IOException {
        goTo(e, "/view/OrderHistory.fxml");
    }


    @FXML void btnLogOut(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SecurityPage.fxml"))));
        stage.show();
    }
}
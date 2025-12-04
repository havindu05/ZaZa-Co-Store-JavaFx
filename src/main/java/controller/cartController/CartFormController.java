package controller.cartController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import model.dto.CartItem;
import service.CartController;

import java.io.IOException;

public class CartFormController {

    @FXML private TableView<CartItem> tblMyCart;
    @FXML private TableColumn<CartItem, String> colItemCode;
    @FXML private TableColumn<CartItem, String> colItem;
    @FXML private TableColumn<CartItem, Double> colPrice;
    @FXML private TableColumn<CartItem, Integer> colQty;
    @FXML private TableColumn<CartItem, Double> colTotal;
    @FXML private TextField txtPrice;

    private final CartController cartService = CartController.getInstance();

    @FXML
    private void initialize() {

        colItemCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemId()));
        colItem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        colQty.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQty()).asObject());
        colTotal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotal()).asObject());


        tblMyCart.setItems(cartService.getAllItems());


        TableColumn<CartItem, Void> actionCol = (TableColumn<CartItem, Void>) tblMyCart.getColumns().get(4);
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button btnRemove = new Button("Remove");
            private final HBox qtyBox = new HBox(8);
            private final Button btnMinus = new Button("-");
            private final Label lblQty = new Label();
            private final Button btnPlus = new Button("+");

            {
                btnRemove.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 20;");
                btnMinus.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-background-radius: 20; -fx-pref-width: 35;");
                btnPlus.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 20; -fx-pref-width: 35;");
                lblQty.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

                qtyBox.getChildren().addAll(btnMinus, lblQty, btnPlus);
                qtyBox.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                    return;
                }
                CartItem cartItem = getTableRow().getItem();
                lblQty.setText(String.valueOf(cartItem.getQty()));

                btnMinus.setOnAction(e -> cartService.updateItemQuantity(cartItem, cartItem.getQty() - 1));
                btnPlus.setOnAction(e -> cartService.updateItemQuantity(cartItem, cartItem.getQty() + 1));
                btnRemove.setOnAction(e -> cartService.removeItem(cartItem));

                setGraphic(new HBox(15, qtyBox, btnRemove));
            }
        });


        cartService.getAllItems().addListener((ListChangeListener<CartItem>) c -> updateTotal());
        updateTotal();
    }

    private void updateTotal() {
    }

    public void btnCheckOut(ActionEvent event) {
    }

    public void btnLogOut(ActionEvent event) {
    }

    public void btnOrders(ActionEvent event) {
    }

    public void btnSupplier(ActionEvent event) {
    }

    public void btnMyCart(ActionEvent event) {
    }

    public void btnProduct(ActionEvent event) {
    }

    public void btnCustomer(ActionEvent event) {
    }

    public void btnEmployee(ActionEvent event) {
    }

    public void btnPlaceOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlaceOrderFrocks.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void btnDashBoard(ActionEvent event) {
    }
}
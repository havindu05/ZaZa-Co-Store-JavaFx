package controller.employeeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.dto.EmployeeDTO;
import service.EmployeeController;

import java.io.IOException;
import java.util.List;

public class EmployeeFormController {

    @FXML private VBox employeeContainer;

    private final EmployeeController employeeService = new EmployeeController();

    @FXML
    private void initialize() {
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        employeeContainer.getChildren().clear();
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            Label noData = new Label("No employees found");
            noData.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 24px;");
            employeeContainer.getChildren().add(noData);
            return;
        }

        for (EmployeeDTO e : employees) {
            VBox card = makeCard(e);
            employeeContainer.getChildren().add(card);
        }
    }

    private VBox makeCard(EmployeeDTO e) {
        VBox card = new VBox(15);

        card.setStyle("""
        -fx-background-color: Blacl;
        -fx-background-radius: 20;
        -fx-border-color: white;
        -fx-border-width: 1;
        -fx-border-radius: 20;
        -fx-padding: 25;
        -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.2), 10, 0.3, 0, 0);
        """);

        card.setPrefWidth(300);
        card.setAlignment(Pos.CENTER);

        Label name = new Label(e.getName());
        name.setStyle("-fx-text-fill: WHITE; -fx-font-size: 24px; -fx-font-weight: bold;");

        Label role = new Label(e.getRole());
        role.setStyle("-fx-text-fill: #3498db; -fx-font-size: 18px;");

        Label phone = new Label(e.getPhone());
        phone.setStyle("-fx-text-fill: #bdc3c7; -fx-font-size: 16px;");

        Label status = new Label(e.isActive() ? "Active" : "On Leave");
        status.setStyle(e.isActive()
                ? "-fx-background-color: #27ae60; -fx-text-fill: white;"
                : "-fx-background-color: #e74c3c; -fx-text-fill: white;");
        status.setPadding(new Insets(10, 30, 10, 30));
        status.setStyle(status.getStyle() + " -fx-background-radius: 30; -fx-font-weight: bold;");

        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("""
        -fx-background-color: #e74c3c;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-background-radius: 25;
        -fx-padding: 10 25;
        -fx-effect: dropshadow(gaussian, #c0392b, 5, 0.5, 0, 2);
        """);

        deleteBtn.setOnAction(event -> {
            employeeService.deleteEmployee(e.getId());
            loadAllEmployees();
        });

        card.getChildren().addAll(name, role, phone, status, deleteBtn);
        return card;
    }

    @FXML void btnDashBoard(ActionEvent e) throws IOException     {
        goTo(e, "/view/DashBoard.fxml");
    }

    @FXML void btnPlaceOrder(ActionEvent e) throws IOException   {
        goTo(e, "/view/PlaceOrderFrocks.fxml");
    }

    @FXML void btnMyCart(ActionEvent e) throws IOException        {
        goTo(e, "/view/CartPage.fxml");
    }

    @FXML void btnCustomer(ActionEvent e) throws IOException     {
        goTo(e, "/view/CustomerForm.fxml");
    }

    @FXML void btnProduct(ActionEvent e) throws IOException      {
        goTo(e, "/view/ProductForm.fxml");
    }

    @FXML void btnEmployee(ActionEvent e) throws IOException     {
        goTo(e, "/view/Employee.fxml");
    }

    @FXML void btnSupplier(ActionEvent e) throws IOException     {
        goTo(e, "/view/SupplierForm.fxml");
    }

    @FXML void btnOrders(ActionEvent e) throws IOException       {
        goTo(e, "/view/OrderHistory.fxml");
    }


    @FXML void btnLogOut(ActionEvent e) throws IOException {
        goTo(e, "/view/SecurityPage.fxml");
    }

    private void goTo(ActionEvent e, String fxml) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxml))));
        stage.centerOnScreen();
        stage.show();
    }
}
package controller.customerController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.CustomerController; // or CustomerServiceImpl

public class CustomerFormController {

    @FXML private TextField txtPhone;
    @FXML private ComboBox<String> cmbTitle;
    @FXML private TextField txtName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPrice;
    @FXML private Spinner<Integer> spnQty;
    @FXML private TextField txtTotal;
    @FXML private Button btnBuyNow;

    private final CustomerService customerService = new CustomerController();

    private double productPrice = 0.0;

    private Runnable onCustomerSaved;

    @FXML
    public void initialize() {
        cmbTitle.getItems().addAll("Mr", "Ms", "Miss");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spnQty.setValueFactory(valueFactory);

        spnQty.valueProperty().addListener((obs, oldValue, newValue) -> calculateTotal());
        txtPrice.textProperty().addListener((obs, oldValue, newValue) -> calculateTotal());
    }

    public void setProductPrice(double price) {
        this.productPrice = price;
        txtPrice.setText(String.format("%.2f", price));
        calculateTotal();
    }

    public void setQuantity(int qty) {
        spnQty.getValueFactory().setValue(qty);
    }

    public void setOnCustomerSaved(Runnable callback) {
        this.onCustomerSaved = callback;
    }

    private void calculateTotal() {
        try {
            double price = txtPrice.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrice.getText());
            int qty = spnQty.getValue();
            txtTotal.setText(String.format("%.2f", price * qty));
        } catch (NumberFormatException e) {
            txtTotal.setText("0.00");
        }
    }

    @FXML
    void btnBuyNow() {
        String phone = txtPhone.getText().trim();

        if (phone.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please enter phone number!");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            showAlert(Alert.AlertType.WARNING, "Phone must be 10 digits!");
            return;
        }

        CustomerDTO existingCustomer = customerService.searchCustomer(phone);
        if (existingCustomer != null) {
            showAlert(Alert.AlertType.INFORMATION, "Customer already exists!\nYou can now place the order.");
        }

        if (cmbTitle.getValue() == null || txtName.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill Title and Name!");
            return;
        }

        CustomerDTO newCustomer = new CustomerDTO(
                phone,
                cmbTitle.getValue(),
                txtName.getText().trim(),
                txtAddress.getText().trim()
        );

        boolean saved = customerService.saveCustomer(newCustomer);

        if (saved || existingCustomer != null) {
            showAlert(Alert.AlertType.INFORMATION, "Customer ready! Proceeding to order...");

            if (onCustomerSaved != null) {
                onCustomerSaved.run();
            }

            Stage stage = (Stage) btnBuyNow.getScene().getWindow();
            stage.close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed to save customer!");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Clothify Store");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtPhone.clear();
        txtName.clear();
        txtAddress.clear();
        txtPrice.clear();
        txtTotal.clear();
        cmbTitle.setValue(null);
        spnQty.getValueFactory().setValue(1);
    }
}
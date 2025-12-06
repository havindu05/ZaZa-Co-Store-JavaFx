package controller.customerController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.CustomerController;

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

    public void setOnCustomerSaved(Runnable callback) {
        this.onCustomerSaved = callback;
    }

    public String getTotalAmount() {
        return txtTotal.getText();
    }

    public double getTotalAmountAsDouble() {
        try {
            return Double.parseDouble(txtTotal.getText());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public int getQuantity() {
        return spnQty.getValue();
    }

    public String getCustomerPhone() {
        return txtPhone.getText().trim();
    }

    public String getCustomerName() {
        return txtName.getText().trim();
    }

    private void calculateTotal() {
        try {
            double price = txtPrice.getText().isEmpty() ? 0.0 : Double.parseDouble(txtPrice.getText());
            int qty = spnQty.getValue();
            txtTotal.setText(String.format("%.2f", price * qty));
        } catch (Exception e) {
            txtTotal.setText("0.00");
        }
    }

    @FXML
    void btnBuyNow() {
        String phone = txtPhone.getText().trim();

        if (phone.isEmpty() || phone.length() != 10 || !phone.matches("\\d{10}")) {
            showAlert(Alert.AlertType.WARNING, "Please enter a valid 10-digit phone number!");
            return;
        }

        if (cmbTitle.getValue() == null || txtName.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please select Title and enter Name!");
            return;
        }

        CustomerDTO existing = customerService.searchCustomer(phone);
        if (existing != null) {
            showAlert(Alert.AlertType.INFORMATION, "Customer already exists! Proceeding to order...");
        }

        CustomerDTO customer = new CustomerDTO(
                phone,
                cmbTitle.getValue(),
                txtName.getText().trim(),
                txtAddress.getText().trim()
        );

        boolean saved = customerService.saveCustomer(customer);

        if (saved || existing != null) {
            showAlert(Alert.AlertType.INFORMATION, "Customer ready! Order confirmed.");

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
}
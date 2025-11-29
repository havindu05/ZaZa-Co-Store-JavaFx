package controller.customerController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.CustomerDTO;
import service.CustomerController;
import service.CustomerService;

public class CustomerFormController {

    @FXML private TextField txtPhone;
    @FXML private ComboBox<String> cmbTitle;
    @FXML private TextField txtName;
    @FXML private TextField txtAddress;
    @FXML private Button btnBuyNow;

    private CustomerService customerService = new CustomerController();

    @FXML
    public void initialize() {
        cmbTitle.getItems().addAll("Mr", "Ms", "Miss");
    }

    @FXML
    void btnBuyNow() {
        String phone = txtPhone.getText().trim();

        if(phone.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter phone number!").show();
            return;
        }

        CustomerDTO existingCustomer = customerService.searchCustomer(phone);

        if(existingCustomer != null) {
            new Alert(Alert.AlertType.INFORMATION, "Customer with this phone number already exists!").show();
            txtPhone.clear();
            txtName.clear();
            txtAddress.clear();
            cmbTitle.setValue(null);
            return;
        }

        if(cmbTitle.getValue() == null || txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all required fields!").show();
            return;
        }

        CustomerDTO newCustomer = new CustomerDTO(
                phone,
                cmbTitle.getValue(),
                txtName.getText(),
                txtAddress.getText()
        );

        boolean saved = customerService.saveCustomer(newCustomer);

        if(saved) {
            new Alert(Alert.AlertType.INFORMATION, "Customer added successfully!").show();
            ((Stage) btnBuyNow.getScene().getWindow()).close();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save customer!").show();
        }
    }
}

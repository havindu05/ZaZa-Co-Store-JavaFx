package service;

import model.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

    boolean saveCustomer(CustomerDTO customerDTO);

    CustomerDTO searchCustomer(String phone);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String phone);

    List<CustomerDTO> getAllCustomers();

}

package repository;

import model.dto.CustomerDTO;
import java.util.List;

public interface CustomerRepository {

    CustomerDTO findByPhone(String phone);

    boolean saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String phone);

    List<CustomerDTO> getAllCustomers();

}

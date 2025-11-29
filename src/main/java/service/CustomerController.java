package service;

import model.dto.CustomerDTO;
import repository.CustomerRepository;
import repository.CustomerRepositoryImpl;

import java.util.List;

public class CustomerController implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        return customerRepository.saveCustomer(customerDTO);
    }

    @Override
    public CustomerDTO searchCustomer(String phone) {
        return customerRepository.findByPhone(phone);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerRepository.updateCustomer(customerDTO);
    }

    @Override
    public boolean deleteCustomer(String phone) {
        return customerRepository.deleteCustomer(phone);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}

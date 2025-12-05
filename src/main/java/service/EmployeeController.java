package service;

import model.dto.EmployeeDTO;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;

import java.util.List;

public class EmployeeController implements EmployeeService {

    private final EmployeeRepository repo = new EmployeeRepositoryImpl();

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public String generateNextId() {
        return "";
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employee) {
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return repo.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        return null;
    }


}
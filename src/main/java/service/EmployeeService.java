package service;

import model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeDTO employee);

    boolean updateEmployee(EmployeeDTO employee);

    boolean deleteEmployee(String id);

    EmployeeDTO getEmployee(String id);

    List<EmployeeDTO> getAllEmployees();

    String generateNextId();

}

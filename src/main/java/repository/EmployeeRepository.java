package repository;

import model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepository {

    boolean save(EmployeeDTO employee);

    boolean update(EmployeeDTO employee);

    boolean delete(String id);

    EmployeeDTO findById(String id);

    List<EmployeeDTO> findAll();

    String generateNextId();

}

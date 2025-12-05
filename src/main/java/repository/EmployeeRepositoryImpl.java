package repository;

import db.DBConnection;
import model.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public List<EmployeeDTO> findAll() {
        List<EmployeeDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection con = DBConnection.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new EmployeeDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("phone"),
                        rs.getBoolean("active")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, id);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override public boolean save(EmployeeDTO e) {
        return false;
    }

    @Override public boolean update(EmployeeDTO e) {
        return false;
    }

    @Override public EmployeeDTO findById(String id) {
        return null;
    }

    @Override public String generateNextId() {
        return "E007";
    }

}
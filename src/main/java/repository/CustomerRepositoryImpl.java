package repository;

import db.DBConnection;
import model.dto.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public CustomerDTO findByPhone(String phone) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM customer WHERE phone=?");
            stm.setString(1, phone);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new CustomerDTO(
                        rs.getString("phone"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement(
                    "INSERT INTO customer(phone, title, name, address) VALUES(?, ?, ?, ?)"
            );
            stm.setString(1, customerDTO.getPhone());
            stm.setString(2, customerDTO.getTitle());
            stm.setString(3, customerDTO.getName());
            stm.setString(4, customerDTO.getAddress());

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement(
                    "UPDATE customer SET title=?, name=?, address=? WHERE phone=?"
            );

            stm.setString(1, customerDTO.getTitle());
            stm.setString(2, customerDTO.getName());
            stm.setString(3, customerDTO.getAddress());
            stm.setString(4, customerDTO.getPhone());

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(String phone) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("DELETE FROM customer WHERE phone=?");
            stm.setString(1, phone);

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM customer");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                list.add(new CustomerDTO(
                        rs.getString("phone"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

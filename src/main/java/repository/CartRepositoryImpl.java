package repository;

import db.DBConnection;
import model.dto.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartRepositoryImpl implements CartRepository {

    private static final String SQL_INSERT =
            "INSERT INTO order_detail (order_id, item_id, qty, unit_price, line_total) VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean saveCart(List<CartItem> items, String orderId) {
        if (items == null || items.isEmpty()) return false;

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT)) {

            con.setAutoCommit(false);

            for (CartItem item : items) {
                preparedStatement.setString(1, orderId);
                preparedStatement.setString(2, item.getItemId());
                preparedStatement.setInt(3, item.getQty());
                preparedStatement.setDouble(4, item.getPrice());
                preparedStatement.setDouble(5, item.getTotal());
                preparedStatement.addBatch();
            }

            int[] results = preparedStatement.executeBatch();
            con.commit();

            return results.length == items.size();

        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection con = DBConnection.getInstance().getConnection()) {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public List<CartItem> getCartByOrderId(String orderId) {
        return null;
    }
}
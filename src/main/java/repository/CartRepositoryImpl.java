package repository;

import db.DBConnection;
import model.dto.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CartRepositoryImpl implements CartRepository{

    @Override
    public boolean saveCart(List<CartItem> items, String orderId) {
        String sql = "INSERT INTO order_detail (order_id, item_id, qty, unit_price, line_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            for (CartItem item : items) {
                preparedStatement.setString(1, orderId);
                preparedStatement.setString(2, item.getItemId());
                preparedStatement.setInt(3, item.getQty());
                preparedStatement.setDouble(4, item.getPrice());
                preparedStatement.setDouble(5, item.getTotal());
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch().length > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartItem> getCartByOrderId(String orderId) {

        return null;
    }
}

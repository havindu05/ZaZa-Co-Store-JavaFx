package repository;

import model.dto.CartItem;

import java.util.List;

public interface CartRepository {

    boolean saveCart(List<CartItem> items, String orderId);

    List<CartItem> getCartByOrderId(String orderId);
}

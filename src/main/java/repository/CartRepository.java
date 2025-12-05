package repository;

import model.dto.CartItemDTO;

import java.util.List;

public interface CartRepository {

    boolean saveCart(List<CartItemDTO> items, String orderId);

    List<CartItemDTO> getCartByOrderId(String orderId);
}

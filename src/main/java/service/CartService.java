package service;

import javafx.collections.ObservableList;
import model.dto.CartItemDTO;

public interface CartService {

    void addItem(CartItemDTO item);

    void removeItem(CartItemDTO item);

    void updateItemQuantity(CartItemDTO item, int newQty);

    void clearCart();

    ObservableList<CartItemDTO> getAllItems();

    double getTotalAmount();

    int getItemCount();

    boolean saveToDatabase(String orderId);

}
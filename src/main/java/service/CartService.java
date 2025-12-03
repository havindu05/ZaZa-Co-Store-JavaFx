package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;

public interface CartService {

    void addItem(CartItem item);

    void removeItem(CartItem item);

    void updateItemQuantity(CartItem item, int newQty);

    void clearCart();

    ObservableList<CartItem> getAllItems();

    double getTotalAmount();

    int getItemCount();

}

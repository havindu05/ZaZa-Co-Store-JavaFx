package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;

public class CartServiceController implements CartService{

    @Override
    public void addItem(CartItem item) {

    }

    @Override
    public void removeItem(CartItem item) {

    }

    @Override
    public void updateItemQuantity(CartItem item, int newQty) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public ObservableList<CartItem> getAllItems() {
        return null;
    }

    @Override
    public double getTotalAmount() {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

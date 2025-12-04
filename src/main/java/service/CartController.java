package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import repository.CartRepository;
import repository.CartRepositoryImpl;

public class CartController implements CartService {

    private static final CartController INSTANCE = new CartController();

    private final ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
    private final CartRepository cartRepository = new CartRepositoryImpl();

    @Override
    public void addItem(CartItem newItem) {

        for (CartItem item : cartItems) {
            if (item.getItemId().equals(newItem.getItemId())) {
                item.setQty(item.getQty() + newItem.getQty());
                return;
            }
        }
        cartItems.add(newItem);
    }

    @Override
    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    @Override
    public void updateItemQuantity(CartItem item, int newQty) {
        if (newQty <= 0) {
            removeItem(item);
        } else {
            item.setQty(newQty);
        }
    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public ObservableList<CartItem> getAllItems() {
        return FXCollections.unmodifiableObservableList(cartItems);
    }

    @Override
    public double getTotalAmount() {
        return cartItems.stream().mapToDouble(CartItem::getTotal).sum();
    }

    @Override
    public int getItemCount() {
        return cartItems.stream().mapToInt(CartItem::getQty).sum();
    }

    @Override
    public boolean saveToDatabase(String orderId) {
        if (cartItems.isEmpty()) return false;
        return cartRepository.saveCart(cartItems, orderId);
    }

    public static CartController getInstance() {
        return INSTANCE;
    }

    private CartController() {

    }
}
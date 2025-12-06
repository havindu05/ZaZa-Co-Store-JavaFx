package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CartItemDTO;
import repository.CartRepository;
import repository.CartRepositoryImpl;

public class CartController implements CartService {

    private static final CartController INSTANCE = new CartController();

    private final ObservableList<CartItemDTO> cartItems = FXCollections.observableArrayList();
    private final CartRepository cartRepository = new CartRepositoryImpl();

    private CartController() {}

    public static CartController getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(CartItemDTO newItem) {
        for (CartItemDTO item : cartItems) {
            if (item.getItemId().equals(newItem.getItemId())) {
                item.setQty(item.getQty() + newItem.getQty());
                item.setTotal(item.getPrice() * item.getQty());
                return;
            }
        }
        cartItems.add(newItem);
    }

    @Override
    public void removeItem(CartItemDTO item) {
        cartItems.remove(item);
    }

    @Override
    public void updateItemQuantity(CartItemDTO item, int newQty) {
        if (newQty <= 0) {
            removeItem(item);
        } else {
            item.setQty(newQty);
            item.setTotal(item.getPrice() * newQty);
        }
    }

    @Override
    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public ObservableList<CartItemDTO> getAllItems() {
        return FXCollections.unmodifiableObservableList(cartItems);
    }

    @Override
    public double getTotalAmount() {
        return cartItems.stream().mapToDouble(CartItemDTO::getTotal).sum();
    }

    @Override
    public int getItemCount() {
        return cartItems.stream().mapToInt(CartItemDTO::getQty).sum();
    }

    @Override
    public boolean saveToDatabase(String orderId) {
        return cartRepository.saveCart(cartItems, orderId);
    }
}
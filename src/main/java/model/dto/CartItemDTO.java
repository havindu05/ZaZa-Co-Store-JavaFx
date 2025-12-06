package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemDTO {

    private String itemId;
    private String name;
    private double price;
    private int qty;
    private double total;

    public CartItemDTO(String itemId, String name, double price, int qty) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.total = price * qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
        this.total = this.price * qty;
    }
}
package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemDTO {

    private String itemId;

    private String name;

    private double price;

    private int qty;

    private String image;

}

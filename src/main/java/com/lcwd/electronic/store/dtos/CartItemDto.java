package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.Cart;
import com.lcwd.electronic.store.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartItemDto {

    private int cartItemId;
    private Product product;

    private int quantity;

    private int totalPrice;
}

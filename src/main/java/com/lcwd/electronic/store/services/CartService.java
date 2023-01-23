package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;

public interface CartService {

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    void removeItemFromCart(String userId,int cartItem);

    void clearCart(String userId);
}

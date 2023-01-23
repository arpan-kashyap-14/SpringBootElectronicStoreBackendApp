package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.CartDto;
import com.lcwd.electronic.store.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@RequestBody AddItemToCartRequest request,
                                                 @PathVariable String userId)
    {
        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(@PathVariable String userId,@PathVariable int itemId)
    {
        cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage response = ApiResponseMessage.builder().
                message("Item is Removed!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId)
    {
        cartService.clearCart(userId);
        ApiResponseMessage response = ApiResponseMessage.builder().
                message("Now Cart is blank!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}

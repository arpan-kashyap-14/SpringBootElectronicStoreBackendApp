package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.dtos.OrderDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody  CreateOrderRequest request)
    {
        OrderDto order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId)
    {
        orderService.removeOrder(orderId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Order Removed Succesully ").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId)
    {
        List<OrderDto> ordersOfUser = orderService.getOrdersOfUser(userId);

        return new ResponseEntity<>(ordersOfUser,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(@PathVariable String userId,
                                                                @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                @RequestParam(value = "sortBy",defaultValue = "orderedDate",required = false) String sortBy,
                                                                @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir

                                                                )
    {

        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


}

package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.dtos.OrderDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(CreateOrderRequest orderDto);

    void removeOrder(String orderId);

    List<OrderDto> getOrdersOfUser(String userId);

    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);

}

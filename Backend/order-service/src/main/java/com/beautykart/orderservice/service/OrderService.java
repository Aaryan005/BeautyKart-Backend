package com.beautykart.orderservice.service;

import com.beautykart.orderservice.dto.OrderRequest;
import com.beautykart.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequest request);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    List<Order> getOrdersByCustomerId(Long customerId);

    Order updateOrderStatus(Long id, String status);

    void deleteOrder(Long id);
}
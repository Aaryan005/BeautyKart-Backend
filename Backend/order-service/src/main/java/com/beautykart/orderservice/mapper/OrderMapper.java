package com.beautykart.orderservice.mapper;

import com.beautykart.orderservice.dto.OrderItemRequest;
import com.beautykart.orderservice.dto.OrderRequest;
import com.beautykart.orderservice.entity.Order;
import com.beautykart.orderservice.entity.OrderItem;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class OrderMapper {


    public static   Order toEntity(OrderRequest request) {

        Order order = new Order();

        order.setCustomerId(request.customerId());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = request.orderItems()
                .stream()
                .map(itemRequest -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(itemRequest.productId());
                    item.setQuantity(itemRequest.quantity());
                    item.setOrder(order);
                    return item;
                })
                .toList();

        order.setOrderItems(orderItems);
        return order;
    }
}
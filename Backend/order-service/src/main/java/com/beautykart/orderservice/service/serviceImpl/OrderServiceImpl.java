package com.beautykart.orderservice.service.serviceImpl;

import com.beautykart.orderservice.client.InventoryClient;
import com.beautykart.orderservice.client.ProductClient;
import com.beautykart.orderservice.dto.InventoryResponse;
import com.beautykart.orderservice.dto.OrderRequest;
import com.beautykart.orderservice.dto.ProductResponse;
import com.beautykart.orderservice.entity.Order;
import com.beautykart.orderservice.entity.OrderItem;
import com.beautykart.orderservice.exception.InsufficientStockException;
import com.beautykart.orderservice.exception.ResourceNotFoundException;
import com.beautykart.orderservice.mapper.OrderMapper;
import com.beautykart.orderservice.repository.OrderRepository;
import com.beautykart.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    @Override
    public Order createOrder(OrderRequest request) {

        Order order = OrderMapper.toEntity(request);

        double totalAmount = 0.0;

        for (OrderItem item : order.getOrderItems()) {

            ProductResponse product =
                    productClient.getProductById(item.getProductId());

            InventoryResponse inventory =
                    inventoryClient.getInventoryByProductId(item.getProductId());

            if (inventory.quantity() < item.getQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock for product id: " + item.getProductId());
            }

            item.setPrice(product.price());

            inventoryClient.reduceStock(
                    item.getProductId(),
                    item.getQuantity()
            );

            totalAmount += product.price() * item.getQuantity();
        }

        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {

        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));

        order.setStatus(status);

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));

        orderRepository.delete(order);
    }
}
package com.MaxiFerrez.crud_orders.service;

import com.MaxiFerrez.crud_orders.model.Order;
import com.MaxiFerrez.crud_orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public List<Order> getOrders() {
        return this.orderRepository.findAll();
    }

    public boolean checkProductAvailability(String sku) {
        String productServiceUrl = "http://localhost:8083/api/products/" + sku;
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(productServiceUrl, Boolean.class);
            Boolean status = response.getBody();
            return Boolean.TRUE.equals(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ResponseEntity<Object> addOrder(Order order) {
        // Validar la disponibilidad del producto antes de guardar el pedido
        String sku = order.getSku();
        if (!checkProductAvailability(sku)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exist or is out of stock");
        }

        // Si el producto está disponible, guardar el pedido
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }

    public ResponseEntity<Object> updateOrder(Long id, Order updateOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setSku(updateOrder.getSku());
            existingOrder.setBuyer(updateOrder.getBuyer());
            existingOrder.setAmount(updateOrder.getAmount());

            orderRepository.save(existingOrder);

            return ResponseEntity.ok("Order updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    public ResponseEntity<Object> deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }
}

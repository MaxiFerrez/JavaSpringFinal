package com.MaxiFerrez.crud_orders.service;

import com.MaxiFerrez.crud_orders.model.Order;
import com.MaxiFerrez.crud_orders.repositories.OrderRepository;
import com.MaxiFerrez.crud_orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrders() {
        Order order = new Order(1L, "SKU001", "Buyer1", 10);
        List<Order> orders = Collections.singletonList(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getOrders();
        assertEquals(1, result.size());
        assertEquals("SKU001", result.get(0).getSku());
    }

    @Test
    public void testAddOrder() {
        Order order = new Order(1L, "SKU001", "Buyer1", 10);

        when(restTemplate.getForEntity(any(String.class), eq(Boolean.class)))
                .thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        ResponseEntity<Object> response = orderService.addOrder(order);

        verify(orderRepository, times(1)).save(order);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        Order existingOrder = new Order(orderId, "SKU001", "Buyer1", 10);
        Order updateOrder = new Order(orderId, "SKU002", "Buyer2", 15);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(existingOrder);

        ResponseEntity<Object> response = orderService.updateOrder(orderId, updateOrder);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order updated successfully", response.getBody());
        assertEquals("SKU002", existingOrder.getSku());
        assertEquals("Buyer2", existingOrder.getBuyer());
        assertEquals(15, existingOrder.getAmount());
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;

        when(orderRepository.existsById(orderId)).thenReturn(true);

        ResponseEntity<Object> response = orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());
    }
}
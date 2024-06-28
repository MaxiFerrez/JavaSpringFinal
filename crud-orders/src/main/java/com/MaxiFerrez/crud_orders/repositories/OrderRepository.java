package com.MaxiFerrez.crud_orders.repositories;

import com.MaxiFerrez.crud_orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
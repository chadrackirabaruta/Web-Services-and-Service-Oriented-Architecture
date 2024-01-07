package com.programmingtechie.orderservice.repository;

import com.programmingtechie.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByOrderLineItemsList_ProductId(Long productId);
}

package com.perfuland.perfulandia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfuland.perfulandia.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIdOrderContainingIgnoreCase(Long id);
    boolean existsByIdOrder(Long id);
}

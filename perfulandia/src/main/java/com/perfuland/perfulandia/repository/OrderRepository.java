package com.perfuland.perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfuland.perfulandia.model.Order;

@Repository // Aunque JpaRepository lo infiere, es bueno dejarlo
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Todos tus métodos ya están cubiertos por JpaRepository<Order, Long>:
    // findById(Long id)
    // existsById(Long id)
    // findAll()
    // deleteById(Long id)
    // save(Order order)

    // No necesitas declarar métodos aquí a menos que sean búsquedas por otros
    // campos,
    // como por ejemplo:
    // List<Order> findByTotalPriceGreaterThan(Long price);
}
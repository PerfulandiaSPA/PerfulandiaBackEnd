package com.perfuland.perfulandia.service;

import com.perfuland.perfulandia.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long idOrder);

    Order createOrder(Order order);

    Order updateOrder(Long idOrder, Order order); //

    void deleteOrder(Long idOrder); //
}
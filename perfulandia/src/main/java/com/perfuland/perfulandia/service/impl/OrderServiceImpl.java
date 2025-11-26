package com.perfuland.perfulandia.service.impl;

import com.perfuland.perfulandia.model.Order;
import com.perfuland.perfulandia.repository.OrderRepository;
import com.perfuland.perfulandia.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderRepository.findById(idOrder)
                .orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long idOrder, Order orderDetails) {
        Optional<Order> orderOptional = orderRepository.findById(idOrder);

        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();

            existingOrder.setDate(orderDetails.getDate());
            existingOrder.setTotalPrice(orderDetails.getTotalPrice());

            // Nota: La lógica para actualizar el 'client' y 'orderDetails'
            // es más compleja y se omite aquí por simplicidad.

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long idOrder) {
        orderRepository.deleteById(idOrder);
    }
}
package com.perfuland.perfulandia.service.impl;
// Ajusta el paquete si no usas 'impl'

import com.perfuland.perfulandia.model.Order;
import com.perfuland.perfulandia.repository.OrderRepository; // Necesitas el repositorio
import com.perfuland.perfulandia.service.OrderService; // Importa la interfaz
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service // ¡ESTA ES LA ANOTACIÓN CLAVE QUE FALTABA!
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
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        // Lógica de negocio si es necesaria (cálculo de total, validación, etc.)
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();

            // Actualiza los campos:
            existingOrder.setDate(orderDetails.getDate());
            existingOrder.setTotalPrice(orderDetails.getTotalPrice());

            // Nota: La lógica para actualizar el 'client' y 'orderDetails'
            // es más compleja y depende de si son nuevos o existentes.
            // Para el arranque, solo actualizamos los campos básicos.

            return orderRepository.save(existingOrder);
        }
        return null; // O lanza una excepción si la orden no existe
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
package com.perfuland.perfulandia.controller;

import com.perfuland.perfulandia.service.OrderService;
import com.perfuland.perfulandia.model.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Obtener todos los pedidos")
    // ... (otras anotaciones)
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Obtener pedidos por ID")
    // ... (otras anotaciones)
    @GetMapping("/{idOrder}") // <-- CORREGIDO: usar idOrder
    public Order getOrderById(@PathVariable Long idOrder) { // <-- CORREGIDO: usar idOrder
        return orderService.getOrderById(idOrder);
    }

    @Operation(summary = "Crear un nuevo pedido")
    // ... (otras anotaciones)
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
    }

    @Operation(summary = "Actualizar un pedido existente")
    @PutMapping("/{idOrder}") // <-- CORREGIDO: usar idOrder
    public Order updateOrder(@PathVariable Long idOrder, @Valid @RequestBody Order order) { // <-- CORREGIDO: usar
                                                                                            // idOrder
        return orderService.updateOrder(idOrder, order);
    }

    @Operation(summary = "Eliminar un pedido")
    @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente")
    @DeleteMapping("/{idOrder}") // <-- CORREGIDO: usar idOrder
    public ResponseEntity<Void> deleteOrder(@PathVariable Long idOrder) { // <-- CORREGIDO: usar idOrder
        orderService.deleteOrder(idOrder);
        return ResponseEntity.noContent().build();
    }
}
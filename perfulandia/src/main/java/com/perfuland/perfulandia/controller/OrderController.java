package com.perfuland.perfulandia.controller;

import com.perfuland.perfulandia.service.OrderService;
import com.perfuland.perfulandia.model.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class))))
    })
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Obtener pedidos por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public Order getOrdersById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "Crear un nuevo pedido")
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente")
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
    }

    @Operation(summary = "Actualizar un pedido existente")
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @Valid @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @Operation(summary = "Eliminar un pedido")
    @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

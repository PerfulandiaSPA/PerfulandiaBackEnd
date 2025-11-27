package com.perfuland.perfulandia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;

    // El detalle apunta a la Orden (ManyToOne)
    // Usamos 'order' como nombre del campo para el mappedBy en Order.java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // El detalle apunta al Perfume (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id", nullable = false)
    private Perfume perfume;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1, message = "La cantidad mínima es 1.")
    private Integer quantity;

    @NotNull(message = "El precio unitario es obligatorio.")
    private Long unitPrice;
}
package com.perfuland.perfulandia.model;

import java.util.List;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Column(nullable = false)
    private LocalDateTime date;


    private String clientId;

    private List<Perfume> orderDetail;

    private Long totalPrice;
}

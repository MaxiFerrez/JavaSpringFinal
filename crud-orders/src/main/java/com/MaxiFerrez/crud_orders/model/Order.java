package com.MaxiFerrez.crud_orders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message =" El codigo no puede estar vacio")
    private String sku;

    @NotBlank(message =" El nombre no puede estar vacio")
    private String buyer;

    @NotNull(message =" Tiene que ingresar una cantidad")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer amount;

}

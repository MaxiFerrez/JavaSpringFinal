package com.ucc.crudservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message =" El codigo sku no puede estar vacio")
    private String sku;

    @NotBlank(message =" El nombre no puede estar vacio")
    private String name;

    @NotBlank(message =" La descripción no puede estar vacio")
    private String description;

    @NotNull(message =" Tiene que tener un valor")
    @DecimalMin(value = "0.0", message ="El valor debe ser mayor a cero")
    private Double price;

    private Boolean status; //Se usa para saber si hay existencia de este producto.


}

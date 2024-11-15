package com.chaquitaclla.microservice.products.products.domain.model.aggregates;

import com.chaquitaclla.microservice.products.products.domain.model.commands.CreateProductCommand;
import com.chaquitaclla.microservice.products.products.domain.model.valueobjects.ProductType;
import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long quantity;

    @NotNull
    private ProductType productType;

    @NotNull
    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "sowing_id", insertable = false, updatable = false)
    private Sowing sowing;

    @NotNull
    @Column(name = "sowing_id")
    private Long sowingId;

    public Product(CreateProductCommand command) {
        this.sowingId = command.sowingId();
        this.name = command.name();
        this.quantity = command.quantity();
        this.productType = command.productType();
        this.appliedDate = LocalDate.now();
    }
    public Product(){

    }
}
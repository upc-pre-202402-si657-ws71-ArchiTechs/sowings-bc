package com.chaquitaclla.microservice.products.products.interfaces.rest.resources;


import com.chaquitaclla.microservice.products.products.domain.model.valueobjects.ProductType;

public record CreateProductResource(Long sowingId, String name, Long quantity, ProductType productType) {
}

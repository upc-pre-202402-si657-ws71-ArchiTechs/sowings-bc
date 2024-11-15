package com.chaquitaclla.microservice.products.products.domain.model.commands;

import com.chaquitaclla.microservice.products.products.domain.model.valueobjects.ProductType;

public record CreateProductCommand(Long sowingId,
                                   String name,
                                   Long quantity,
                                   ProductType productType) {
}
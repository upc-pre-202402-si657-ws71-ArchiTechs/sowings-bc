package com.chaquitaclla.microservice.products.products.interfaces.rest.resources;


import com.chaquitaclla.microservice.products.products.domain.model.valueobjects.ProductType;

import java.time.LocalDate;

public record ProductResource(Long id, Long sowingId, String name, Long quantity, LocalDate appliedDate,
                              ProductType productType) {
}

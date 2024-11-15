package com.chaquitaclla.microservice.products.products.interfaces.rest.transform;


import com.chaquitaclla.microservice.products.products.domain.model.commands.CreateProductCommand;
import com.chaquitaclla.microservice.products.products.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(Long sowingId, CreateProductResource resource) {
        return new CreateProductCommand(
                sowingId,
                resource.name(),
                resource.quantity(),
                resource.productType());
    }
}

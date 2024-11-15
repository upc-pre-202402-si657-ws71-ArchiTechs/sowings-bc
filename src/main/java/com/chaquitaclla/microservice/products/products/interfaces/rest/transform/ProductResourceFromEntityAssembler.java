package com.chaquitaclla.microservice.products.products.interfaces.rest.transform;


import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import com.chaquitaclla.microservice.products.products.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(product.getId(), product.getSowingId(), product.getName(), product.getQuantity(), product.getAppliedDate(), product.getProductType());
    }
}
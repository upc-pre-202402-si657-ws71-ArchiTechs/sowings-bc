package com.chaquitaclla.microservice.products.products.domain.services;


import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import com.chaquitaclla.microservice.products.products.domain.model.queries.GetProductByIdQuery;

import java.util.Optional;

public interface ProductQueryService {
    /*List<Product> handle(GetProductsBySowingIdQuery query);*/
    Optional<Product> handle(GetProductByIdQuery query);
}

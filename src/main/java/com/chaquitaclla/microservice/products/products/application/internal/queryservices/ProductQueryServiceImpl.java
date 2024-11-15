package com.chaquitaclla.microservice.products.products.application.internal.queryservices;


import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import com.chaquitaclla.microservice.products.products.domain.model.queries.GetProductByIdQuery;
import com.chaquitaclla.microservice.products.products.domain.services.ProductQueryService;
import com.chaquitaclla.microservice.products.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

/*
    @Override
    public List<Product> handle(GetProductsBySowingIdQuery query) {
        return this.productRepository.findAll();
    }
*/
    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return this.productRepository.findById(query.productId());
    }

}

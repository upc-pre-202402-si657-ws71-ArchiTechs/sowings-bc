package com.chaquitaclla.microservice.products.products.application.internal.commandservices;

import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import com.chaquitaclla.microservice.products.products.domain.model.commands.CreateProductCommand;
import com.chaquitaclla.microservice.products.products.domain.model.commands.DeleteProductCommand;
import com.chaquitaclla.microservice.products.products.domain.services.ProductCommandService;
import com.chaquitaclla.microservice.products.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        var product = new Product(command);
        try{
            productRepository.save(product);
        } catch(Exception e){
            throw new IllegalArgumentException("Error while saving product." + e.getMessage());
        }
        return product.getId();
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if(!productRepository.existsById(command.id())){
            throw new IllegalArgumentException("Product with id " + command.id() + " does not exist.");

        }
        productRepository.deleteById(command.id());
    }
}

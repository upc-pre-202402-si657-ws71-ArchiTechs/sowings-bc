package com.chaquitaclla.microservice.products.products.infrastructure.persistence.jpa.repositories;


import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /*List<Product> findProductsBySowingId(Long sowingId);*/
    List<Product> findBySowingId(Long sowingId);

    Optional<Product> findById(Long id);
}

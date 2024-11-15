package com.chaquitaclla.microservice.products.sowings.interfaces.rest;

import com.chaquitaclla.microservice.products.products.domain.services.ProductCommandService;
import com.chaquitaclla.microservice.products.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.chaquitaclla.microservice.products.products.interfaces.rest.resources.CreateProductResource;
import com.chaquitaclla.microservice.products.products.interfaces.rest.resources.ProductResource;
import com.chaquitaclla.microservice.products.products.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.chaquitaclla.microservice.products.products.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetAllSowingsQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingByIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingCommandService;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingQueryService;
import com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources.CreateSowingResource;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources.SowingResource;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources.UpdateSowingResource;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.transform.CreateSowingCommandFromResourceAssembler;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.transform.SowingResourceFromEntityAssembler;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.transform.UpdateSowingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sowings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sowings", description = "Sowing Management Endpoints")
public class SowingsController {
    private final SowingCommandService sowingCommandService;
    private final SowingQueryService sowingQueryService;
    private final SowingRepository sowingRepository;

    private final ProductCommandService productCommandService;
    private final ProductRepository productRepository;

    public SowingsController(SowingCommandService sowingCommandService,
                             SowingRepository sowingRepository,
                             SowingQueryService sowingQueryService,
                             ProductCommandService productCommandService,
                             ProductRepository productRepository) {
        this.sowingCommandService = sowingCommandService;
        this.sowingRepository = sowingRepository;
        this.sowingQueryService = sowingQueryService;
        this.productCommandService = productCommandService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<SowingResource> createSowing(@RequestBody CreateSowingResource createSowingResource) {
        var createSowingCommand = CreateSowingCommandFromResourceAssembler.fromResource(createSowingResource);
        var sowingId = sowingCommandService.handle(createSowingCommand);
        if(sowingId == 0L) return ResponseEntity.badRequest().build();

        var sowing = sowingRepository.findById(sowingId).orElseThrow();

        var sowingResourceCreated = SowingResourceFromEntityAssembler.fromEntity(sowing);

        return new ResponseEntity<>(sowingResourceCreated, HttpStatus.CREATED);
    }
    @GetMapping("/look-crop/{cropId}")
    public ResponseEntity<?> findCropById(@PathVariable Long cropId) {
        return ResponseEntity.ok(sowingQueryService.findCropById(cropId));
    }
    @GetMapping()
    public ResponseEntity<List<SowingResource>> getAllSowings(){
        var getAllSowingsQuery = new GetAllSowingsQuery();
        var sowings = sowingQueryService.handle(getAllSowingsQuery);
        var sowingResource = sowings.stream()
                .map(SowingResourceFromEntityAssembler::fromEntity)
                .toList();
        return ResponseEntity.ok(sowingResource);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SowingResource> updateSowing(@PathVariable Long id, @RequestBody UpdateSowingResource updateSowingResource) {
        var updateSowingCommand = UpdateSowingCommandFromResourceAssembler.fromResource(id, updateSowingResource);
        var updatedSowing = sowingCommandService.handle(updateSowingCommand);

        if (updatedSowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var updatedSowingResource = SowingResourceFromEntityAssembler.fromEntity(updatedSowing.get());

        return ResponseEntity.ok(updatedSowingResource);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SowingResource> getSowing(@PathVariable Long id) {
        return sowingQueryService.handle(new GetSowingByIdQuery(id))
                .map(SowingResourceFromEntityAssembler::fromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/{sowingId}/products")
    public ResponseEntity<ProductResource> createProduct(@PathVariable Long sowingId, @RequestBody CreateProductResource createProductResource) {
        var sowing = sowingRepository.findById(sowingId);
        if (sowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(sowingId, createProductResource);
        var productId = productCommandService.handle(createProductCommand);
        if(productId == 0L) return ResponseEntity.badRequest().build();

        var product = productRepository.findById(productId).orElseThrow();

        var productResourceCreated = ProductResourceFromEntityAssembler.toResourceFromEntity(product);

        return new ResponseEntity<>(productResourceCreated, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSowing(@PathVariable Long id) {
        var sowing = sowingRepository.findById(id);
        if (sowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sowingRepository.deleteById(id);

        return ResponseEntity.ok("Sowing with given id successfully deleted.");
    }
    @DeleteMapping("/{sowingId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long sowingId, @PathVariable Long productId) {
        // Check if the sowing exists
        var sowing = sowingRepository.findById(sowingId);
        if (sowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(productId);

        return ResponseEntity.ok("Product with given id successfully deleted.");
    }

    @GetMapping("/{sowingId}/products")
    public ResponseEntity<List<ProductResource>> getProductsBySowingId(@PathVariable Long sowingId) {
        var sowing = sowingRepository.findById(sowingId);
        if (sowing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var products = productRepository.findBySowingId(sowingId);

        var productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResources);
    }
}
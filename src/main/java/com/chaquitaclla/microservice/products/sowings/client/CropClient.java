package com.chaquitaclla.microservice.products.sowings.client;

import com.chaquitaclla.microservice.products.sowings.dto.CropDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "microservice-crops", url = "https://cropsmsvc-c4bjdzhbd4hug6fa.canadacentral-01.azurewebsites.net/api/v1/crops")
public interface CropClient {
    @GetMapping("/{id}")
    CropDTO findCropById(@PathVariable("id") Long id);
}
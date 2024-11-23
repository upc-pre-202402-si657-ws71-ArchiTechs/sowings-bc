package com.chaquitaclla.microservice.products.sowings.client;

import com.chaquitaclla.microservice.products.sowings.dto.ProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "microservice-profiles", url = "https://profileschaquitaclla.azurewebsites.net/api/v1/profiles")
public interface ProfileClient {
    @GetMapping("/{profileId}")
    ProfileDTO findProfileById(@PathVariable("profileId") Long profileId);
}
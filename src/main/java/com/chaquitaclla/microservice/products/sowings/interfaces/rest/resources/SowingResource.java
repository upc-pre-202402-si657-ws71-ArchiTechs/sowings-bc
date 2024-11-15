package com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources;


import com.chaquitaclla.microservice.products.shared.domain.model.valueobjects.DateRange;

public record SowingResource(Long id, DateRange dateRange, Integer profileId, Integer areaLand, boolean status, Integer cropId, Integer phenologicalPhase) {
}
package com.chaquitaclla.microservice.products.sowings.domain.model.commands;

public record UpdateSowingCommand(Long Id, Integer cropId, Integer areaLand) {
}
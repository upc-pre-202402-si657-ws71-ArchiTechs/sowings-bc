package com.chaquitaclla.microservice.products.sowings.domain.model.commands;

import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.ProfileId;

public record CreateSowingCommand(Integer cropId, Integer areaLand, ProfileId profileId){
}
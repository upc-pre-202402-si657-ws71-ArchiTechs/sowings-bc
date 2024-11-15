package com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources;


import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingCondition;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingSoilMoisture;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingStemCondition;

public record UpdateSowingControlResource(SowingCondition sowingCondition,
                                          SowingSoilMoisture sowingSoilMoisture,
                                          SowingStemCondition sowingStemCondition) {
}

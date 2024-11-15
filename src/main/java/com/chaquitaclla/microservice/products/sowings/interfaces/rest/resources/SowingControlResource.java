package com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources;

import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingCondition;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingSoilMoisture;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.SowingStemCondition;

import java.util.Date;

public record SowingControlResource(Long id,
                                    Long sowingId,
                                    Date controlDate,
                                    SowingCondition sowingCondition,
                                    SowingSoilMoisture sowingSoilMoisture,
                                    SowingStemCondition sowingStemCondition) {
}
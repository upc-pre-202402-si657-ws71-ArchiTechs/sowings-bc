package com.chaquitaclla.microservice.products.sowings.interfaces.rest.transform;


import com.chaquitaclla.microservice.products.sowings.domain.model.commands.UpdateSowingCommand;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources.UpdateSowingResource;

public class UpdateSowingCommandFromResourceAssembler {
    public static UpdateSowingCommand fromResource(Long sowingId, UpdateSowingResource resource) {
        return new UpdateSowingCommand(
                sowingId,
                resource.cropId(),
                resource.areaLand()
        );
    }
}
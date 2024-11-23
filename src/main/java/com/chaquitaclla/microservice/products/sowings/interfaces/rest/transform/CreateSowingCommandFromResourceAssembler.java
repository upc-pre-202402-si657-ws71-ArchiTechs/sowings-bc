package com.chaquitaclla.microservice.products.sowings.interfaces.rest.transform;

import com.chaquitaclla.microservice.products.sowings.domain.model.commands.CreateSowingCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.ProfileId;
import com.chaquitaclla.microservice.products.sowings.interfaces.rest.resources.CreateSowingResource;

public class CreateSowingCommandFromResourceAssembler {
    public static CreateSowingCommand fromResource(CreateSowingResource resource) {
        return new CreateSowingCommand(
                resource.cropId(),
                resource.areaLand(),
                new ProfileId(resource.profileId())
        );
    }
}
package com.chaquitaclla.microservice.products.sowings.domain.services;

import com.chaquitaclla.microservice.products.sowings.domain.model.commands.CreateSowingControlCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.commands.UpdateSowingControlCommand;

public interface SowingControlCommandService {
    Long handle(CreateSowingControlCommand command);
    void handle(DeleteSowingControlBySowingIdCommand command);
    void handle(UpdateSowingControlCommand command);
}
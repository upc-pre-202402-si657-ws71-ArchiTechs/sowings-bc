package com.chaquitaclla.microservice.products.sowings.domain.services;


import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import com.chaquitaclla.microservice.products.sowings.domain.model.entities.SowingControl;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetAllSowingControlsQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingControlByIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;

import java.util.List;
import java.util.Optional;

public interface SowingControlQueryService {
    List<SowingControl> handle(GetAllSowingControlsQuery query);
    List<SowingControl> handle(GetSowingControlsBySowingIdQuery query);
    Optional<SowingControl> handle(GetSowingControlByIdQuery query);
}

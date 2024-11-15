package com.chaquitaclla.microservice.products.sowings.application.internal.queryservices;


import com.chaquitaclla.microservice.products.sowings.domain.model.entities.SowingControl;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetAllSowingControlsQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingControlByIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingControlQueryService;
import com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories.SowingControlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SowingControlQueryServiceImpl implements SowingControlQueryService {

    private final SowingControlRepository sowingControlRepository;

    public SowingControlQueryServiceImpl(SowingControlRepository sowingControlRepository) {
        this.sowingControlRepository = sowingControlRepository;
    }


    @Override
    public List<SowingControl> handle(GetAllSowingControlsQuery query) {
        return sowingControlRepository.findAll();
    }

    @Override
    public List<SowingControl> handle(GetSowingControlsBySowingIdQuery query) {
        return sowingControlRepository.findBySowingId(query.sowingId());
    }
    @Override
    public Optional<SowingControl> handle(GetSowingControlByIdQuery query) {
        return sowingControlRepository.findById(query.Id());
    }

}
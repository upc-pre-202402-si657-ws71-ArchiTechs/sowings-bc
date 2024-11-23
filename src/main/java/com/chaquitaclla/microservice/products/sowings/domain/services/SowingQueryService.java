package com.chaquitaclla.microservice.products.sowings.domain.services;


import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetAllSowingsQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingByIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingsByPhenologicalPhaseQuery;
import com.chaquitaclla.microservice.products.sowings.dto.ProfileDTO;
import com.chaquitaclla.microservice.products.sowings.http.response.CropByIdResponse;
import com.chaquitaclla.microservice.products.sowings.http.response.ProfileByIdResponse;

import java.util.List;
import java.util.Optional;

public interface SowingQueryService {
    List<Sowing> handle(GetAllSowingsQuery query);
    Optional<Sowing> handle(GetSowingByIdQuery query);
    List<Sowing> handle(GetSowingsByPhenologicalPhaseQuery query);
    CropByIdResponse findCropById(Long id);
    ProfileByIdResponse findProfileById(Long id);
}
package com.chaquitaclla.microservice.products.sowings.application.internal.queryservices;

import com.chaquitaclla.microservice.products.sowings.client.CropClient;
import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetAllSowingsQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingByIdQuery;
import com.chaquitaclla.microservice.products.sowings.domain.model.queries.GetSowingsByPhenologicalPhaseQuery;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingQueryService;
import com.chaquitaclla.microservice.products.sowings.dto.CropDTO;
import com.chaquitaclla.microservice.products.sowings.http.response.CropByIdResponse;
import com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SowingQueryServiceImpl implements SowingQueryService {

    private final SowingRepository sowingRepository;

    @Autowired
    private CropClient cropClient;

    public SowingQueryServiceImpl(SowingRepository sowingRepository, CropClient cropClient) {
        this.sowingRepository = sowingRepository;
        this.cropClient = cropClient;
    }

    @Override
    public List<Sowing> handle(GetAllSowingsQuery query) {
        return sowingRepository.findAll();
    }

    @Override
    public Optional<Sowing> handle(GetSowingByIdQuery query) {
        return sowingRepository.findById(query.id());
    }

    @Override
    public List<Sowing> handle(GetSowingsByPhenologicalPhaseQuery query) {
        return sowingRepository.findByPhenologicalPhase(query.phase());
    }

    @Override
    public CropByIdResponse findCropById(Long id) {
        CropDTO cropDTO = cropClient.findCropById(id);
        return CropByIdResponse.builder()
                .id(cropDTO.getId())
                .name(cropDTO.getName())
                .description(cropDTO.getDescription())
                .imageUrl(cropDTO.getImageUrl())
                .diseaseIds(cropDTO.getDiseaseIds())
                .pestIds(cropDTO.getPestIds())
                .careIds(cropDTO.getCareIds())
                .build();
    }

}
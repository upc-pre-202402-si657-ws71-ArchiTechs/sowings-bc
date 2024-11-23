package com.chaquitaclla.microservice.products.sowings.application.internal.commandservices;

import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import com.chaquitaclla.microservice.products.sowings.domain.model.commands.CreateSowingCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.commands.DeleteSowingCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.commands.UpdateSowingCommand;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.CropId;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.ProfileId;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingCommandService;
import com.chaquitaclla.microservice.products.sowings.domain.services.SowingQueryService;
import com.chaquitaclla.microservice.products.sowings.http.response.CropByIdResponse;
import com.chaquitaclla.microservice.products.sowings.http.response.ProfileByIdResponse;
import com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SowingCommandServiceImpl implements SowingCommandService {
    private final SowingRepository sowingRepository;
    private final SowingQueryService sowingQueryService;

    @Autowired
    public SowingCommandServiceImpl(SowingRepository sowingRepository, SowingQueryService sowingQueryService) {
        this.sowingRepository = sowingRepository;
        this.sowingQueryService = sowingQueryService;
    }

    @Override
    public Long handle(CreateSowingCommand command) {
        CropByIdResponse cropResponse = sowingQueryService.findCropById(Long.valueOf(command.cropId()));
        ProfileByIdResponse profileResponse = sowingQueryService.findProfileById(command.profileId().profileId());
        if (cropResponse == null) {
            throw new IllegalArgumentException("The crop with the given ID does not exist.");
        }
        if (profileResponse == null) {
            throw new IllegalArgumentException("The profile with the given ID does not exist.");
        }

        var cropId = new CropId(Long.valueOf(command.cropId()));
        var profileId = new ProfileId(profileResponse.getId());
        var sowing = new Sowing(cropId, command.areaLand(), profileId);
        try {
            sowingRepository.save(sowing);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error saving sowing");
        }

        return sowing.getId();
    }

    @Override
    public Optional<Sowing> handle(UpdateSowingCommand command) {
        if (!sowingRepository.existsById(command.Id()))
            throw new IllegalArgumentException("Sowing does not exist");

        var sowingToUpdate = sowingRepository.findById(command.Id()).get();
        Integer cropIdInteger = command.cropId();
        CropId cropId = new CropId(Long.valueOf(cropIdInteger));
        sowingToUpdate.setCropId(cropId);
        sowingToUpdate.setAreaLand(command.areaLand());
        var updatedSowing = sowingRepository.save(sowingToUpdate);

        return Optional.of(updatedSowing);
    }

    @Override
    public void handle(DeleteSowingCommand command) {
        if (!sowingRepository.existsById(command.sowingId()))
            throw new IllegalArgumentException("Sowing does not exist");

        sowingRepository.deleteById(command.sowingId());
    }
}
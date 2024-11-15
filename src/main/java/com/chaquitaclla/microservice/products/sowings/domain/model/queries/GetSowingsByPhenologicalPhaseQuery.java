package com.chaquitaclla.microservice.products.sowings.domain.model.queries;


import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.PhenologicalPhase;

public record GetSowingsByPhenologicalPhaseQuery(PhenologicalPhase phase) {
}
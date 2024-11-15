package com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories;


import com.chaquitaclla.microservice.products.sowings.domain.model.aggregates.Sowing;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.PhenologicalPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SowingRepository extends JpaRepository<Sowing, Long>{
    List<Sowing> findByPhenologicalPhase(PhenologicalPhase phase);
    Optional<Sowing> findById(Long id);
}

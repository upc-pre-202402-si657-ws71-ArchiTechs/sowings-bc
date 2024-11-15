package com.chaquitaclla.microservice.products.sowings.infrastructure.persistence.jpa.repositories;
import com.chaquitaclla.microservice.products.sowings.domain.model.entities.SowingControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SowingControlRepository extends JpaRepository<SowingControl, Long>{
    List<SowingControl> findBySowingId(Long aLong);
    Optional<SowingControl> findByIdAndSowingId(Long id, Long sowingId);

}

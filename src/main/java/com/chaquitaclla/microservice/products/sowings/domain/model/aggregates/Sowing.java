package com.chaquitaclla.microservice.products.sowings.domain.model.aggregates;

import com.chaquitaclla.microservice.products.products.domain.model.aggregates.Product;
import com.chaquitaclla.microservice.products.shared.domain.model.valueobjects.DateRange;
import com.chaquitaclla.microservice.products.sowings.domain.model.entities.SowingControl;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.CropId;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.PhenologicalPhase;
import com.chaquitaclla.microservice.products.sowings.domain.model.valueobjects.ProfileId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sowing {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private DateRange dateRange;

    @Embedded
    private ProfileId profileId;

    @Getter
    @NotNull
    private int areaLand;

    @Getter
    @NotNull
    private boolean status;

    @OneToMany(mappedBy = "sowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SowingControl> sowingControls;

    @Embedded
    private CropId cropId;

    @OneToMany(mappedBy = "sowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> sowingProducts;

    @Getter
    private PhenologicalPhase phenologicalPhase;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public CropId getCropId() {
        return cropId;
    }

    public ProfileId getProfileId() {
        return profileId;
    }

    protected Sowing() {
    }

    public Sowing(CropId cropId, Integer areaLand, ProfileId profileId) {
        LocalDate startDate = LocalDate.now();
        this.dateRange = new DateRange(startDate, 6);
        this.areaLand = areaLand;
        this.cropId = cropId;
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
        this.profileId = profileId;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void addSowingControl(SowingControl sowingControl) {
        if (sowingControls == null) {
            sowingControls = new ArrayList<>();
        }
        sowingControls.add(sowingControl);
        sowingControl.setSowing(this);
    }

    public void germinationPhase() {
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }

    public void harvestingPhase() {
        this.phenologicalPhase = PhenologicalPhase.HARVEST_READY;
    }
}
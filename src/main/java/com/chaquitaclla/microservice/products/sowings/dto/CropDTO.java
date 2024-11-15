package com.chaquitaclla.microservice.products.sowings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private List<Long> diseaseIds;
    private List<Long> pestIds;
    private List<Long> careIds;
}
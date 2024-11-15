package com.chaquitaclla.microservice.products.sowings.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropByIdResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private List<Long> diseaseIds;
    private List<Long> pestIds;
    private List<Long> careIds;
}
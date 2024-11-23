package com.chaquitaclla.microservice.products.sowings.dto;

import lombok.*;

/**
 * Response:
 * public record ProfileResource(Long id, String fullName, String email, String streetAddress) {
 * }
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String fullName;
    private String email;
    private String streetAddress;
}
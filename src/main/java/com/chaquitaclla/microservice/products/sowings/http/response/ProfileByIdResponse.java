package com.chaquitaclla.microservice.products.sowings.http.response;
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
public class ProfileByIdResponse {
    private Long id;
    private String fullName;
    private String email;
    private String streetAddress;
}
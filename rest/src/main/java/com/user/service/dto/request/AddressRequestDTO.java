package com.user.service.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AddressRequestDTO {
    @NotEmpty(message = "fullAddress is required")
    private String fullAddress;
    @NotNull(message = "zipCode is required")
    private int zipCode;
    @NotEmpty(message = "city is required")
    private String city;
}

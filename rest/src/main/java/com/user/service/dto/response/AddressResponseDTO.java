package com.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AddressResponseDTO {
    private String addressId;
    private String fullAddress;
    private int zipCode;
    private String city;
}

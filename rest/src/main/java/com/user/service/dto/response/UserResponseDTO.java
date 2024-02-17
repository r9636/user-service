package com.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserResponseDTO {
    private String userId;
    private String name;
    private List<AddressResponseDTO> address;
}

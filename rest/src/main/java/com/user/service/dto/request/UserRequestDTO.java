package com.user.service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserRequestDTO {
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "address is required")
    @Valid
    private List<AddressRequestDTO> address;
}

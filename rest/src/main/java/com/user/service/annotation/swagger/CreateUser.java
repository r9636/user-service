package com.user.service.annotation.swagger;

import com.user.service.dto.response.UserResponseDTO;
import com.user.service.exception.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Create User API")
@ApiResponses(value = {
        @ApiResponse(description = "Created User", responseCode = "200",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserResponseDTO.class))}),
        @ApiResponse(description = "User not created", responseCode = "500",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = Error.class))})
})
public @interface CreateUser {
}

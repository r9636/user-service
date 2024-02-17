package com.user.service.resource.impl;

import com.user.service.dto.request.UserRequestDTO;
import com.user.service.dto.response.UserResponseDTO;
import com.user.service.resource.UserResource;
import com.user.service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@Validated
public class UserResourceImpl implements UserResource {
    private final UserService userService;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> retrieveUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}

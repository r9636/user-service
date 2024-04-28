package com.user.service.resource;

import com.user.service.annotation.swagger.CreateUser;
import com.user.service.annotation.swagger.RetrieveUser;
import com.user.service.dto.request.UserRequestDTO;
import com.user.service.dto.response.UserResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface UserResource {
    @CreateUser
    ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);
    @RetrieveUser
    ResponseEntity<UserResponseDTO> retrieveUser(String userId);
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserResponseDTO>> retrieveAllUsers();
}

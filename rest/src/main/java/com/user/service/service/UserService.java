package com.user.service.service;

import com.user.service.dto.request.UserRequestDTO;
import com.user.service.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUser(String userId);
}

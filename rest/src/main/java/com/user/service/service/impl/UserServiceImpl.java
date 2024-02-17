package com.user.service.service.impl;

import com.user.service.dto.request.UserRequestDTO;
import com.user.service.dto.response.UserResponseDTO;
import com.user.service.entity.Address;
import com.user.service.entity.User;
import com.user.service.exception.UserNotFoundException;
import com.user.service.repo.UserRepo;
import com.user.service.service.UserService;
import com.user.service.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = modelMapper.map(userRequestDTO, User.class);
        UUID uuid = UUID.getUuid();
        user.setUserId(uuid.getNewId());
        List<Address> addresses = user.getAddress().stream().map(address -> {
            address.setAddressId(uuid.getNewId());
            address.setUser(user);
            return address;
        }).collect(Collectors.toList());
        user.setAddress(addresses);
        userRepo.save(user);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUser(String userId) {
        Optional<User> user = userRepo.findByUserId(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
       return modelMapper.map(user.get(), UserResponseDTO.class);
    }
}

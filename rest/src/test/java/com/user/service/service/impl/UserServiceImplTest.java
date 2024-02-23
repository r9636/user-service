package com.user.service.service.impl;

import com.user.service.dto.request.AddressRequestDTO;
import com.user.service.dto.request.UserRequestDTO;
import com.user.service.dto.response.AddressResponseDTO;
import com.user.service.dto.response.UserResponseDTO;
import com.user.service.entity.Address;
import com.user.service.entity.User;
import com.user.service.exception.UserNotFoundException;
import com.user.service.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    User user;
    UserRequestDTO userRequestDTO;
    UserResponseDTO userResponseDTO;
    Address address;
    AddressRequestDTO addressRequestDTO;
    AddressResponseDTO addressResponseDTO;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepo userRepo;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
         addressRequestDTO = AddressRequestDTO.builder()
                .fullAddress("Chinchwad").city("Pune").zipCode(411033).build();
         userRequestDTO = UserRequestDTO.builder()
                .name("Ramesh").address(List.of(addressRequestDTO)).build();
         addressResponseDTO = AddressResponseDTO.builder()
                .fullAddress("Chinchwad").city("Pune").zipCode(411033).build();
         userResponseDTO = UserResponseDTO.builder()
                .name("Ramesh").address(List.of(addressResponseDTO)).build();
         address = Address.builder().fullAddress("Chinchwad").city("Pune").zipCode(411033).build();
         user = User.builder().name("Ramesh").address(List.of(address)).build();
    }

    @Test
    public void createUserWhenCorrectDataIsProvided() {
        when(modelMapper.map(userRequestDTO, User.class)).thenReturn(user);
        doReturn(user).when(userRepo).save(user);
        when(modelMapper.map(user, UserResponseDTO.class)).thenReturn(userResponseDTO);
        userServiceImpl.createUser(userRequestDTO);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void retrieveUserWhenCorrectDataIsProvided() {
        String userId = "123";
        user.setUserId(userId);
        userResponseDTO.setUserId(userId);
        Optional<User> user = Optional.of(this.user);
        when(userRepo.findByUserId(userId)).thenReturn(user);
        when(modelMapper.map(user.get(), UserResponseDTO.class)).thenReturn(userResponseDTO);
        assertNotNull(userServiceImpl.getUser(userId));
    }

    @Test
    public void retrieveUserWhenInCorrectDataIsProvidedThrowsException() {
        String userId = "123";
        when(userRepo.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUser(userId));
    }

    @Test
    public void retrieveUserWhenInCorrectDataIsProvidedThrowsExceptionAndValidateMessage() {
        String userId = "123";
        when(userRepo.findByUserId(userId)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUser(userId));
        assertEquals("User with id 123 not found", exception.getLocalizedMessage());
    }
}
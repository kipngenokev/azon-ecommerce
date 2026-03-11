package com.azon.users.service;

import com.azon.users.dtos.CreateUserRequest;
import com.azon.users.dtos.UserResponseDto;
import com.azon.users.entity.User;
import com.azon.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(CreateUserRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());

        User savedUser =userRepository.save(user);

        return getUserResponseDto(savedUser);


    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::getUserResponseDto)
                .collect(Collectors.toList());

    }

    private UserResponseDto mapToResponseDto(User user) {
        return getUserResponseDto(user);
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}

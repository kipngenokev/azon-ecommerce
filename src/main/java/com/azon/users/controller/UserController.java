package com.azon.users.controller;

import com.azon.users.dtos.CreateUserRequest;
import com.azon.users.dtos.UserResponseDto;
import com.azon.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequest request) {

        UserResponseDto newUser = userService.createUser(request);

        return ResponseEntity
                .created(URI.create("/api/users/" + newUser.getId()))
                .body(newUser);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}

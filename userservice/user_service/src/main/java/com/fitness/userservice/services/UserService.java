package com.fitness.userservice.services;

import com.fitness.userservice.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public  UserResponse register(RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())){
            User exisitingUser = repository.findByEmail(request.getEmail());
            UserResponse userResponse = new UserResponse();
            userResponse.setId(exisitingUser.getId());
            userResponse.setPassword(exisitingUser.getPassword());
            userResponse.setEmail(exisitingUser.getEmail());
            userResponse.setFirstName(exisitingUser.getFirstName());
            userResponse.setLastName(exisitingUser.getLastName());
            userResponse.setCreatedAt(exisitingUser.getCreatedAt());
            userResponse.setUpdatedAt(exisitingUser.getUpdatedAt());
            return userResponse;
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setKeycloakId(request.getKeycloakId());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setKeycloakId(savedUser.getKeycloakId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponse;

    }

    public  UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));


        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }

    public Boolean existByUserId(String userId) {
        log.info("Calling User service for {}",userId);
        return repository.existsByKeycloakId(userId);
    }
}

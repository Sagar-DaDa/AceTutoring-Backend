package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.exceptions.UserApiException;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.mapper.UserRoleMapper;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.security.HashEncoder;
import com.acetutoring.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepo.existsByUserName(userDto.getUserName())){
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }

        if (userRepo.existsByEmailAddress(userDto.getEmailAddress())){
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User newUser = UserMapper.mapToUser(userDto);
        newUser.setPassword(HashEncoder.getEncryptedHash(userDto.getPassword()));
        return UserMapper.mapToUserDto(userRepo.save(newUser));
    }

    @Override
    public UserDto getUserById(Long userId) {
        return UserMapper.mapToUserDto(
                userRepo.findById(userId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "User not found. Invalid user ID: " + userId
                                )
                        )
        );
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo
                .findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User foundUser = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User not found. Invalid user ID: " + userId
                )
        );
        foundUser.setFullName(userDto.getFullName());
        foundUser.setMobile(userDto.getMobile());
        foundUser.setEmailAddress(userDto.getEmailAddress());
        foundUser.setPassword(HashEncoder.getEncryptedHash(userDto.getPassword()));
        foundUser.setRole(UserRoleMapper.mapToUserRole(userDto.getRole()));
        return UserMapper.mapToUserDto(userRepo.save(foundUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User foundUser = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User not found. Invalid user ID: " + userId
                )
        );
        userRepo.delete(foundUser);
    }

    @Override
    public boolean isUserExistsWithEmail(String email) {
        return userRepo.findByEmailAddress(email).isPresent();
    }
}

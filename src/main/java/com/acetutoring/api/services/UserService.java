package com.acetutoring.api.services;

import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    boolean isUserExistsWithEmail(String emailAddress);

}

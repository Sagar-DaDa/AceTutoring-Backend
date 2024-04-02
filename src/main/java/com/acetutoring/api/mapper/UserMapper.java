package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFullName(),
                user.getUserName(),
                user.getEmailAddress(),
                user.getMobile(),
                UserRoleMapper.mapToUserRoleDto(user.getRole()),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFullName(),
                userDto.getUserName(),
                userDto.getEmailAddress(),
                userDto.getMobile(),
                userDto.getPassword(),
                UserRoleMapper.mapToUserRole(userDto.getRole()),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }
}

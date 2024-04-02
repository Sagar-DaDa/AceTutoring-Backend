package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.UserRoleDto;
import com.acetutoring.api.entities.UserRole;

public class UserRoleMapper {
    public static UserRoleDto mapToUserRoleDto(UserRole userRole){
        return new UserRoleDto(
                userRole.getId(),
                userRole.getRoleName(),
                userRole.getDescription(),
                userRole.getCreatedAt(),
                userRole.getUpdatedAt()
        );
    }

    public static UserRole mapToUserRole(UserRoleDto userRoleDto){
        return new UserRole(
                userRoleDto.getId(),
                userRoleDto.getRoleName(),
                userRoleDto.getDescription(),
                userRoleDto.getCreatedAt(),
                userRoleDto.getUpdatedAt()
        );
    }
}

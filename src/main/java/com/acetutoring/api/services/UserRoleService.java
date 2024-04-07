package com.acetutoring.api.services;

import com.acetutoring.api.dto.UserRoleDto;
import com.acetutoring.api.entities.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRoleDto createUserRole(UserRoleDto userRoleDto);

    UserRoleDto getUserRoleById(Long userRoleId);

    UserRole findByRoleName(String roleName);

    List<UserRoleDto> getAllUserRoles();

    UserRoleDto updateUserRole(Long userRoleId, UserRoleDto userRoleDto);

    void deleteUserRole(Long userRoleId);

}

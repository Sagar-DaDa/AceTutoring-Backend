package com.acetutoring.api.services;

import com.acetutoring.api.dto.UserRoleDto;

import java.util.List;

public interface UserRoleService {
    UserRoleDto createUserRole(UserRoleDto userRoleDto);

    UserRoleDto getUserRoleById(Long userRoleId);

    UserRoleDto getUserRoleByRoleName(String roleName);

    List<UserRoleDto> getAllUserRoles();

    UserRoleDto updateUserRole(Long userRoleId, UserRoleDto userRoleDto);

    void deleteUserRole(Long userRoleId);

}

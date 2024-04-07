package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.UserRoleDto;
import com.acetutoring.api.entities.UserRole;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.UserRoleMapper;
import com.acetutoring.api.repositories.UserRoleRepo;
import com.acetutoring.api.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepo userRoleRepo;

    @Override
    public UserRoleDto createUserRole(UserRoleDto userRoleDto) {
        return UserRoleMapper.mapToUserRoleDto(
                userRoleRepo.save(UserRoleMapper.mapToUserRole(userRoleDto))
        );
    }

    @Override
    public UserRoleDto getUserRoleById(Long userRoleId) {
        return UserRoleMapper.mapToUserRoleDto(
                userRoleRepo.findById(userRoleId).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User role not found. Invalid user role ID: " + userRoleId
                        )
                )
        );
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        return userRoleRepo
                .findByRoleName(roleName)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User role not found."
                        )
                );
    }

    @Override
    public List<UserRoleDto> getAllUserRoles() {
        return userRoleRepo
                .findAll()
                .stream()
                .map(UserRoleMapper::mapToUserRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleDto updateUserRole(Long userRoleId, UserRoleDto userRoleDto) {
        UserRole foundUserRole = userRoleRepo
                .findById(userRoleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User role not found. Invalid user role ID: " + userRoleId
                        )
                );
        foundUserRole.setRoleName(userRoleDto.getRoleName());
        foundUserRole.setDescription(userRoleDto.getDescription());
        return UserRoleMapper.mapToUserRoleDto(userRoleRepo.save(foundUserRole));
    }

    @Override
    public void deleteUserRole(Long userRoleId) {
        UserRole foundUserRole = userRoleRepo
                .findById(userRoleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User role not found. Invalid user role ID: " + userRoleId
                        )
                );
        userRoleRepo.delete(foundUserRole);
    }
}

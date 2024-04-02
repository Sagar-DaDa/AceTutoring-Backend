package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.UserRoleDto;
import com.acetutoring.api.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/userRoles")
public class UserRoleController {

    private UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody UserRoleDto userRoleDto){
        return new ResponseEntity<>(userRoleService.createUserRole(userRoleDto), HttpStatus.CREATED);
    }

    @GetMapping("{userRoleId}")
    public ResponseEntity<UserRoleDto> getUserRoleById(@PathVariable Long userRoleId){
        return ResponseEntity.ok(userRoleService.getUserRoleById(userRoleId));
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles(){
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @PutMapping("{userRoleId}")
    public ResponseEntity<UserRoleDto> updateUserRoleById(
            @PathVariable Long userRoleId,
            @RequestBody UserRoleDto userRoleDto){
        return ResponseEntity.ok(userRoleService.updateUserRole(userRoleId, userRoleDto));
    }

    @DeleteMapping("{userRoleId}")
    public String deleteUserRoleById(@PathVariable Long userRoleId){
        userRoleService.deleteUserRole(userRoleId);
        return "User role deleted successfully.";
    }


}

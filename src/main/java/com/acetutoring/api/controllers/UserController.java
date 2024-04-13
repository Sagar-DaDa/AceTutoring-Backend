package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUserById(
            @PathVariable Long userId,
            @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUserById(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "User deleted successfully.";
    }
}

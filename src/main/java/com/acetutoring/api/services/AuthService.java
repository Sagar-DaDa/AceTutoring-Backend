package com.acetutoring.api.services;

import com.acetutoring.api.dto.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthService {
    String studentLogin(LoginDto loginDto);

    void resetPassword(String email);
}

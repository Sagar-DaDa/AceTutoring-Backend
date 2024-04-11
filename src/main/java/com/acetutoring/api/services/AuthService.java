package com.acetutoring.api.services;

import com.acetutoring.api.dto.LoginDto;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthService {
    String studentLogin(LoginDto loginDto);

    String adminLogin(LoginDto loginDto);

    void resetPassword(String email);

    void changePassword(Long userId, String newPassword);

    void changeStudentPassword(Long studentId, String newPassword);

}

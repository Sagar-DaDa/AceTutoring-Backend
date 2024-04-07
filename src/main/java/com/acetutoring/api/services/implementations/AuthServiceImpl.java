package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.LoginDto;
import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.Student;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.repositories.StudentRepo;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.services.AuthService;
import com.acetutoring.api.services.UserService;
import com.acetutoring.api.utils.EmailSender;
import com.acetutoring.api.utils.PasswordEncoderImpl;
import com.acetutoring.api.utils.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private StudentRepo studentRepo;
    private UserRepo userRepo;
    private UserService userService;

    @Autowired
    private EmailSender emailSender;
//    private PasswordEncoder passwordEncoder;

    @Override
    public String studentLogin(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Student student = studentRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Student not found. Invalid student email."
                ));
        // Get the logged-in user's ID
        return String.valueOf(student.getId());
    }

    @Override
    public void resetPassword(String email) {
        User foundUser = userRepo.findByUserName(email).orElseThrow(() -> new UsernameNotFoundException(
                "User not found. Invalid user email."
        ));

        String newPassword = PasswordGenerator.generatePassword();

        foundUser.setPassword(PasswordEncoderImpl.encode(newPassword));

        userRepo.save(foundUser);

        String subject = "Password Reset";
        String body = "Dear User,\n\n" +
                "Your password has been reset!\n\n" +
                "Your login details are as below:\n" +
                "username: " + email + "\n" +
                "password: " + newPassword + "\n\n\n\n" +
                "Best regards,\n" +
                "Ace Tutoring";

        emailSender.sendMail(email, subject, body); //Email sent
    }
}

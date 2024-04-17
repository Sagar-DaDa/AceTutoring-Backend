package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.entities.UserRole;
import com.acetutoring.api.enums.UserRoleEnum;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.exceptions.UserApiException;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.mapper.UserRoleMapper;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.repositories.UserRoleRepo;
import com.acetutoring.api.security.HashEncoder;
import com.acetutoring.api.services.UserRoleService;
import com.acetutoring.api.services.UserService;
import com.acetutoring.api.utils.EmailSender;
import com.acetutoring.api.utils.PasswordEncoderImpl;
import com.acetutoring.api.utils.PasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserRoleService userRoleService;
    private EmailSender emailSender;

    @Override
    public UserDto createCustomer(UserDto userDto) {
        if (userRepo.existsByUserName(userDto.getUserName())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }

        if (userRepo.existsByEmailAddress(userDto.getEmailAddress())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User newUser = UserMapper.mapToUser(userDto);
        newUser.setPassword(PasswordEncoderImpl.encode(userDto.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRoleService.findByRoleName(UserRoleEnum.ROLE_CUSTOMER.toString()));
        newUser.setRoles(roles);

        return UserMapper.mapToUserDto(userRepo.save(newUser));
    }

    @Override
    public UserDto createAdmin(String adminEmail) {
        if (userRepo.existsByUserName("admin")) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }

        if (userRepo.existsByEmailAddress(adminEmail)) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User newUser = new User();
        newUser.setUserName("admin");
        newUser.setFullName("Admin");
        newUser.setMobile("0000000000");
        newUser.setEmailAddress(adminEmail);

        String generatedPassword = PasswordGenerator.generatePassword(8);

        newUser.setPassword(PasswordEncoderImpl.encode(generatedPassword));

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRoleService.findByRoleName(UserRoleEnum.ROLE_ADMIN.toString()));
        newUser.setRoles(roles);

        UserDto createdAdmin = UserMapper.mapToUserDto(userRepo.save(newUser));

        String subject = "Admin Registered";
        String body = "Dear Admin,\n\n" +
                "Thank you for registering as admin on Ace Tutoring.\n\n" +
                "Your login details are as below:\n" +
                "username: " + adminEmail + "\n" +
                "password: " + generatedPassword + "\n\n" +
                "Please login to your account for further administration.\n\n\n\n" +
                "Best regards,\n" +
                "Ace Tutoring";

        emailSender.sendMail(adminEmail, subject, body);

        return createdAdmin;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepo.existsByUserName(userDto.getUserName())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }

        if (userRepo.existsByEmailAddress(userDto.getEmailAddress())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }

        User newUser = UserMapper.mapToUser(userDto);
        newUser.setPassword(PasswordEncoderImpl.encode(userDto.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRoleService.findByRoleName(UserRoleEnum.ROLE_OPERATOR.toString()));
        newUser.setRoles(roles);

        return UserMapper.mapToUserDto(userRepo.save(newUser));
    }

    @Override
    public UserDto getUserById(Long userId) {
        User foundUser = userRepo.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found. Invalid user ID: " + userId
                )
        );

        return new UserDto(
                foundUser.getId(),
                foundUser.getFullName(),
                foundUser.getUserName(),
                foundUser.getEmailAddress(),
                foundUser.getMobile(),
                foundUser.getCreatedAt(),
                foundUser.getUpdatedAt()
        );
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo
                .findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        User foundUser = userRepo.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User not found. Invalid user ID: " + userId
                )
        );

        userRepo.delete(foundUser);
    }

    @Override
    public boolean isUserExistsWithEmail(String emailAddress) {
        return false;
    }

    @Override
    public boolean isAdminExists() {
        return userRepo.existsByUserName("admin");
    }

    @Override
    public Long totalUsersCount() {
        return userRepo.count();
    }

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        if (userRepo.existsByUserName(userDto.getUserName())){
//            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
//        }
//
//        if (userRepo.existsByEmailAddress(userDto.getEmailAddress())){
//            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists.");
//        }
//
//        User newUser = UserMapper.mapToUser(userDto);
//        newUser.setPassword(HashEncoder.getEncryptedHash(userDto.getPassword()));
//        return UserMapper.mapToUserDto(userRepo.save(newUser));
//    }
//
//    @Override
//    public UserDto getUserById(Long userId) {
//        return UserMapper.mapToUserDto(
//                userRepo.findById(userId)
//                        .orElseThrow(
//                                () -> new ResourceNotFoundException(
//                                        "User not found. Invalid user ID: " + userId
//                                )
//                        )
//        );
//    }
//
//    @Override
//    public List<UserDto> getAllUsers() {
//        return userRepo
//                .findAll()
//                .stream()
//                .map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDto updateUser(Long userId, UserDto userDto) {
//        User foundUser = userRepo.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        "User not found. Invalid user ID: " + userId
//                )
//        );
//        foundUser.setFullName(userDto.getFullName());
//        foundUser.setMobile(userDto.getMobile());
//        foundUser.setEmailAddress(userDto.getEmailAddress());
//        foundUser.setPassword(HashEncoder.getEncryptedHash(userDto.getPassword()));
//        return UserMapper.mapToUserDto(userRepo.save(foundUser));
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        User foundUser = userRepo.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException(
//                        "User not found. Invalid user ID: " + userId
//                )
//        );
//        userRepo.delete(foundUser);
//    }
//
//    @Override
//    public boolean isUserExistsWithEmail(String email) {
//        return userRepo.findByEmailAddress(email).isPresent();
//    }
}

package com.acetutoring.api.dto;

import com.acetutoring.api.entities.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String userName;
    private String emailAddress;
    private String mobile;
    private String password;
    private Set<UserRole> roles;
    private Date createdAt;
    private Date updatedAt;

    public UserDto(
            Long id,
            String fullName,
            String userName,
            String emailAddress,
            String mobile,
            Set<UserRole> roles,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.mobile = mobile;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDto(
            Long id,
            String fullName,
            String userName,
            String emailAddress,
            String mobile,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.mobile = mobile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDto(
            String fullName,
            String userName,
            String emailAddress,
            String mobile,
            String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.mobile = mobile;
        this.password = password;
    }
}

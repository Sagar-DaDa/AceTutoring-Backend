package com.acetutoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private String roleName;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public UserRoleDto(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}

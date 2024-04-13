package com.acetutoring.api.dto;

import com.acetutoring.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private Long id;
    private String title;
    private String message;
    private UserDto createdBy;
    private Date createdAt;
    private Date updatedAt;

    public NoticeDto(String title, String message) {
        this.title = title;
        this.message = message;
    }
}

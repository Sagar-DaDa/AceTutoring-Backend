package com.acetutoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AceFileDto {
    private Long id;
    private String name;
    private String description;
    private String fileUrl;
    private Date createdAt;
}

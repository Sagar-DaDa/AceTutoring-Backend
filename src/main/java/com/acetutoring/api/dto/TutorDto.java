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
public class TutorDto {
    private Long id;
    private String fullName;
    private String gender;
    private String address;
    private String email;
    private String mobile;
    private String qualifications;
    private String experienceInyears;
    private String specialization;
    private String workingOrganization;
    private String teachingPhilosophy;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
}

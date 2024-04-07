package com.acetutoring.api.dto;

import com.acetutoring.api.entities.AvailableCourse;
import com.acetutoring.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String studentName;
    private String parentName;
    private String contactNumber;
    private String email;
    private String school;
    private String additionalInformation;
    private UserDto userId;
    private boolean acceptTerms;
    private Date createdAt;
    private Date updatedAt;

    public StudentDto(
            String studentName,
            String parentName,
            String contactNumber,
            String email,
            String school,
            String additionalInformation) {
        this.studentName = studentName;
        this.parentName = parentName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.school = school;
        this.additionalInformation = additionalInformation;
    }

    public StudentDto(
            Long id,
            String studentName,
            String parentName,
            String contactNumber,
            String email,
            String school) {
        this.id = id;
        this.studentName = studentName;
        this.parentName = parentName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.school = school;
    }
}

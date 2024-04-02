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
public class EnrollmentDto {
    private Long id;
    private AvailableCourseDto enrolledCourse;
    private StudentDto enrolledStudent;
    private Date courseStartDate;
    private Date courseEndDate;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;

    public EnrollmentDto(AvailableCourseDto enrolledCourse, StudentDto enrolledStudent) {
        this.enrolledCourse = enrolledCourse;
        this.enrolledStudent = enrolledStudent;
    }
}

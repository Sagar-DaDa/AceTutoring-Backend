package com.acetutoring.api.dto;

import com.acetutoring.api.services.AvailableCourseService;
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
    private Long enrolledCourseId;
    private AvailableCourseDto enrolledCourse;
    private StudentDto enrolledStudent;
    private Date courseStartDate;
    private Date courseEndDate;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;

    private AvailableCourseService availableCourseService;

    public EnrollmentDto(
            Long id,
            AvailableCourseDto enrolledCourse,
            StudentDto enrolledStudent,
            Date courseStartDate,
            Date courseEndDate,
            boolean active,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.enrolledCourse = enrolledCourse;
        this.enrolledStudent = enrolledStudent;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public EnrollmentDto(AvailableCourseDto enrolledCourse, StudentDto enrolledStudent) {
        this.enrolledCourse = enrolledCourse;
        this.enrolledStudent = enrolledStudent;
    }

    public EnrollmentDto(Long enrolledCourseId, StudentDto enrolledStudent) {
        this.enrolledCourse = availableCourseService.getAvailableCourseById(enrolledCourseId);
        this.enrolledStudent = enrolledStudent;
    }
}

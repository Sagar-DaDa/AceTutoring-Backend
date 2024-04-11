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
public class CourseDto {
    private Long id;
    private String courseCode;
    private String courseName;
    private String description;
    private String grade;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;

    public CourseDto(String courseCode, String courseName, String description, String grade, String imageUrl) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.grade = grade;
        this.imageUrl = imageUrl;
    }

    public CourseDto(String courseCode, String courseName, String description, String grade) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.grade = grade;
    }
}

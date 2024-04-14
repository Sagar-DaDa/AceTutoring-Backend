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
public class AvailableCourseDto {
    private Long id;
    private CourseDto course;
    private String duration;
    private String category;
    private String classDays;
    private String classStartTime;
    private String classEndTime;
    private double fees;
    private TutorDto tutor;
    private UserDto createdBy;
    private Date createdAt;
    private Date updatedAt;

    public AvailableCourseDto(Long id) {
        this.id = id;
    }

    public AvailableCourseDto(Long id, CourseDto course) {
        this.id = id;
        this.course = course;
    }
}

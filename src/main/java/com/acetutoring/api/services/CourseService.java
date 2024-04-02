package com.acetutoring.api.services;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.dto.TutorDto;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);

    CourseDto getCourseById(Long courseId);

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(Long courseId, CourseDto courseDto);

    void deleteCourse(Long courseId);
}

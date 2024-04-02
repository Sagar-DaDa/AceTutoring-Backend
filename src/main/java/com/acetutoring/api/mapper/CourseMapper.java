package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.entities.Course;

public class CourseMapper {

    public static CourseDto mapToCourseDto(Course course){
        return new CourseDto(
                course.getId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getDescription(),
                course.getGrade(),
                course.getImageUrl(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }

    public static Course mapToCourse(CourseDto courseDto){
        return new Course(
                courseDto.getId(),
                courseDto.getCourseCode(),
                courseDto.getCourseName(),
                courseDto.getDescription(),
                courseDto.getGrade(),
                courseDto.getImageUrl(),
                courseDto.getCreatedAt(),
                courseDto.getUpdatedAt()
        );
    }
}

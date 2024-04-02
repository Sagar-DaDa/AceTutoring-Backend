package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.entities.AvailableCourse;

public class AvailableCourseMapper {
    public static AvailableCourseDto mapToAvailableCourseDto(AvailableCourse course){
        return new AvailableCourseDto(
                course.getId(),
                CourseMapper.mapToCourseDto(course.getCourse()),
                course.getDuration(),
                course.getCategory(),
                course.getClassDays(),
                course.getClassStartTime(),
                course.getClassEndTime(),
                TutorMapper.mapToTutorDto(course.getTutor()),
                UserMapper.mapToUserDto(course.getCreatedBy()),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }

    public static AvailableCourse mapToAvailableCourse(AvailableCourseDto availableCourseDto){
        return new AvailableCourse(
                availableCourseDto.getId(),
                CourseMapper.mapToCourse(availableCourseDto.getCourse()),
                availableCourseDto.getDuration(),
                availableCourseDto.getCategory(),
                availableCourseDto.getClassDays(),
                availableCourseDto.getClassStartTime(),
                availableCourseDto.getClassEndTime(),
                TutorMapper.mapToTutor(availableCourseDto.getTutor()),
                UserMapper.mapToUser(availableCourseDto.getCreatedBy()),
                availableCourseDto.getCreatedAt(),
                availableCourseDto.getUpdatedAt()
        );
    }
}

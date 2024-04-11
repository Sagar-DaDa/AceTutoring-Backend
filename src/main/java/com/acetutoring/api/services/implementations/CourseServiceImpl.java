package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.entities.Course;
import com.acetutoring.api.exceptions.CourseCodeAlreadyExistsException;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.CourseMapper;
import com.acetutoring.api.repositories.CourseRepo;
import com.acetutoring.api.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepo;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course newCourse = CourseMapper.mapToCourse(courseDto);
        return CourseMapper.mapToCourseDto(courseRepo.save(newCourse));
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Course foundCourse = courseRepo.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course not found. Invalid course ID:" + courseId)
        );
        return CourseMapper.mapToCourseDto(foundCourse);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepo.findAll().stream().map(CourseMapper::mapToCourseDto).toList();
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        Course foundCourse = courseRepo
                .findById(courseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Course not found. Invalid course ID: " + courseId)
                );
        foundCourse.setCourseCode(courseDto.getCourseCode());
        foundCourse.setCourseName(courseDto.getCourseName());
        foundCourse.setDescription(courseDto.getDescription());
        foundCourse.setGrade(courseDto.getGrade());
        foundCourse.setImageUrl(courseDto.getImageUrl());
        return CourseMapper.mapToCourseDto(courseRepo.save(foundCourse));
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course foundCourse = courseRepo
                .findById(courseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Course not found. Invalid course ID: " + courseId)
                );
        courseRepo.delete(foundCourse);
    }

    @Override
    public Long totalCoursesCount() {
        return courseRepo.count();
    }

    @Override
    public boolean isCourseCodeExists(String courseCode) {
        return courseRepo.findByCourseCode(courseCode).isPresent();
    }


}

package com.acetutoring.api.services;

import com.acetutoring.api.dto.AvailableCourseDto;

import java.util.List;

public interface AvailableCourseService {
    AvailableCourseDto createAvailableCourse(AvailableCourseDto availableCourseDto);

    AvailableCourseDto getAvailableCourseById(Long availableCourseId);

    List<AvailableCourseDto> getAllAvailableCourses();

    AvailableCourseDto updateAvailableCourseById(Long availableCourseId, AvailableCourseDto availableCourseDto);

    void deleteAvailableCourseById(Long availableCourseId);

    Long totalAvailableCoursesCount();
}

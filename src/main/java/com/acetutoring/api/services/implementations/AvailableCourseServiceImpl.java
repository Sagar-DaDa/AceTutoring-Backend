package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.entities.AvailableCourse;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.AvailableCourseMapper;
import com.acetutoring.api.mapper.CourseMapper;
import com.acetutoring.api.mapper.TutorMapper;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.repositories.AvailableCourseRepo;
import com.acetutoring.api.services.AvailableCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailableCourseServiceImpl implements AvailableCourseService {
    private AvailableCourseRepo availableCourseRepo;

    @Override
    public AvailableCourseDto createAvailableCourse(AvailableCourseDto availableCourseDto) {
        return AvailableCourseMapper.mapToAvailableCourseDto(
                availableCourseRepo.save(AvailableCourseMapper.mapToAvailableCourse(availableCourseDto))
        );
    }

    @Override
    public AvailableCourseDto getAvailableCourseById(Long availableCourseId) {
        AvailableCourse foundAvailableCourse = availableCourseRepo.findById(availableCourseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Course not available. Invalid course ID: " + availableCourseId
                        )
                );
        return AvailableCourseMapper.mapToAvailableCourseDto(foundAvailableCourse);
    }

    @Override
    public List<AvailableCourseDto> getAllAvailableCourses() {
        return availableCourseRepo
                .findAll()
                .stream()
                .map(AvailableCourseMapper::mapToAvailableCourseDto)
                .toList();
    }

    @Override
    public AvailableCourseDto updateAvailableCourseById(
            Long availableCourseId,
            AvailableCourseDto availableCourseDto) {
        AvailableCourse foundAvailableCourse = availableCourseRepo.findById(availableCourseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Course not available. Invalid course ID: " + availableCourseId
                        )
                );

        foundAvailableCourse.setCourse(CourseMapper.mapToCourse(availableCourseDto.getCourse()));
        foundAvailableCourse.setDuration(availableCourseDto.getDuration());
        foundAvailableCourse.setCategory(availableCourseDto.getCategory());
        foundAvailableCourse.setClassDays(availableCourseDto.getClassDays());
        foundAvailableCourse.setClassStartTime(availableCourseDto.getClassStartTime());
        foundAvailableCourse.setClassEndTime(availableCourseDto.getClassEndTime());
        foundAvailableCourse.setFees(availableCourseDto.getFees());
        foundAvailableCourse.setTutor(TutorMapper.mapToTutor(availableCourseDto.getTutor()));
        foundAvailableCourse.setCreatedBy(UserMapper.mapToUser(availableCourseDto.getCreatedBy()));

        return AvailableCourseMapper.mapToAvailableCourseDto(availableCourseRepo.save(foundAvailableCourse));
    }

    @Override
    public void deleteAvailableCourseById(Long availableCourseId) {
        AvailableCourse foundAvailableCourse = availableCourseRepo.findById(availableCourseId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Course not available. Invalid course ID: " + availableCourseId
                        )
                );
        availableCourseRepo.delete(foundAvailableCourse);
    }
}

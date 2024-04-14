package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.entities.AvailableCourse;
import com.acetutoring.api.entities.Course;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.AvailableCourseMapper;
import com.acetutoring.api.mapper.CourseMapper;
import com.acetutoring.api.mapper.TutorMapper;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.repositories.AvailableCourseRepo;
import com.acetutoring.api.repositories.CourseRepo;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.services.AvailableCourseService;
import com.acetutoring.api.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AvailableCourseServiceImpl implements AvailableCourseService {
    private AvailableCourseRepo availableCourseRepo;
    private UserRepo userRepo;
    private CourseRepo courseRepo;

    @Override
    public AvailableCourseDto createAvailableCourse(AvailableCourseDto availableCourseDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = new User();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        user = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found. Invalid user email."
                ));

        availableCourseDto.setCreatedBy(UserMapper.mapToUserDto(user));

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

    @Override
    public Long totalAvailableCoursesCount() {
        return availableCourseRepo.count();
    }

    @Override
    public List<AvailableCourseDto> getPopularCourses(int limit) {
        List<Object[]> results = availableCourseRepo.findPopularCoursesWithEnrollmentCountWithLimit(limit);
        List<AvailableCourseDto> popularCoursesList = new ArrayList<>();

        for (Object[] result : results) {
            AvailableCourse availableCourse = new AvailableCourse();

            Course course = courseRepo.findById((Long) result[8]).orElseThrow();

            AvailableCourseDto popularCourse = new AvailableCourseDto(
                    (Long)result[0],
                    CourseMapper.mapToCourseDto(course)
            );

            popularCoursesList.add(popularCourse);
        }

        return popularCoursesList;
    }
}

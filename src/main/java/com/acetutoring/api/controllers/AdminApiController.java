package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.dto.TotalCountsDto;
import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/admin/api")
public class AdminApiController {

    private UserService userService;
    private StudentService studentService;
    private TutorService tutorService;
    private EnrollmentService enrollmentService;
    private CourseService courseService;
    private AvailableCourseService availableCourseService;

    @GetMapping("/adminInfo/{userId}")
    public ResponseEntity<UserDto> getAdminById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/totalCounts")
    public ResponseEntity<TotalCountsDto> getTotalCounts(){
        TotalCountsDto totalCountsDto = new TotalCountsDto(
                studentService.totalStudentCount(),
                tutorService.totalTutorCount(),
                userService.totalUsersCount(),
                enrollmentService.totalActiveEnrollmentsCount(),
                enrollmentService.totalInactiveEnrollmentsCount(),
                courseService.totalCoursesCount(),
                availableCourseService.totalAvailableCoursesCount()
        );

        return ResponseEntity.ok(totalCountsDto);
    }

    @GetMapping("/enrollmentsByCategory")
    public ResponseEntity<List<Object[]>> getAllEnrollmentsLastByGroup(){
        List<Object[]> enrollmentList = enrollmentService.getEnrollmentsByCategory();
        return ResponseEntity.ok(enrollmentList);
    }

}

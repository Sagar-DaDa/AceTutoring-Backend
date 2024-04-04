package com.acetutoring.api.controllers;

import com.acetutoring.api.services.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/student/api")
public class StudentApiController {
    private EnrollmentService enrollmentService;

    @GetMapping("/hasActiveEnrollment/{studentId}")
    public Long hasActiveEnrollment(@PathVariable Long studentId){
        return enrollmentService.hasActiveEnrollment(studentId);
    }
}

package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.EnrollmentDto;
import com.acetutoring.api.services.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/hasFinishedEnrollment/{studentId}")
    public Long hasFinishedEnrollment(@PathVariable Long studentId){
        return enrollmentService.hasFinishedEnrollment(studentId);
    }

    @GetMapping("/allEnrollmentsByStudentId/{studentId}")
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollmentsByStudentId(
            @PathVariable Long studentId){
        return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByStudentId(studentId));
    }

}

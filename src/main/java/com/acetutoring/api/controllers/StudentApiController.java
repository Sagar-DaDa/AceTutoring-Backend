package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.ConfirmEnrollmentDto;
import com.acetutoring.api.dto.EnrollmentDto;
import com.acetutoring.api.dto.LoginDto;
import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.services.AuthService;
import com.acetutoring.api.services.EnrollmentService;
import com.acetutoring.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/student/api")
public class StudentApiController {
    private EnrollmentService enrollmentService;
    private AuthService authService;
    private StudentService studentService;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.studentLogin(loginDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/studentInfo/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping("/confirmEnrollment/{enrollmentId}")
    public String confirmEnrollment(@PathVariable Long enrollmentId,
                                    @RequestBody ConfirmEnrollmentDto confirmEnrollmentDto
    ){
        enrollmentService.confirmEnrollment(enrollmentId, confirmEnrollmentDto);
        return "Enrollment confirmed.";
    }
}

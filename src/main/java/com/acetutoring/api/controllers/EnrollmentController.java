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
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentDto> createEnrollment(
            @RequestBody EnrollmentDto enrollmentDto){
        return ResponseEntity.ok(enrollmentService.createEnrollment(enrollmentDto));
    }

    @GetMapping("{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable Long enrollmentId){
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollment(){
        return ResponseEntity.ok(enrollmentService.getAllEnrollment());
    }

    @PutMapping("{enrollmentId}")
    public ResponseEntity<EnrollmentDto> updateEnrollmentById(
            @PathVariable Long enrollmentId,
            @RequestBody EnrollmentDto enrollmentDto){
        return ResponseEntity.ok(enrollmentService.updateEnrollmentById(enrollmentId, enrollmentDto));
    }

    @DeleteMapping("{enrollmentId}")
    public String deleteEnrollmentById(@PathVariable Long enrollmentId){
        enrollmentService.deleteEnrollmentById(enrollmentId);
        return "Enrollment deleted successfully.";
    }
}

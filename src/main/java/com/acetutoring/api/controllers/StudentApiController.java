package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.*;
import com.acetutoring.api.services.AuthService;
import com.acetutoring.api.services.EnrollmentService;
import com.acetutoring.api.services.NoticeService;
import com.acetutoring.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/student/api")
public class StudentApiController {
    private EnrollmentService enrollmentService;
    private AuthService authService;
    private StudentService studentService;
    private NoticeService noticeService;

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

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<NoticeDto> getNoticeById(@PathVariable Long noticeId){
        return ResponseEntity.ok(noticeService.getNoticeById(noticeId));
    }

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDto>> getAllNotices(){
        return ResponseEntity.ok(noticeService.getAllNotice());
    }

    @PutMapping("/changeStudentPassword/{studentId}")
    public String changeStudentPassword(
            @PathVariable Long studentId,
            @RequestBody Map<String, String> newPasswordMap){
        authService.changeStudentPassword(studentId, newPasswordMap.get("newPassword"));

        return "Password changed successfully";
    }

    @PostMapping("/newEnrollmentForExistingStudent")
    public String createNewEnrollmentForExistingStudent(
            @RequestBody EnrollmentForExistingStudentDto enrollmentForExistingStudentDto
    ){
        enrollmentService.createdNewEnrollmentForExistingStudent(enrollmentForExistingStudentDto);

        return "New enrollment created successfully.";
    }
}

package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDto> createStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public String deleteStudentById(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);

        return "Student deleted successfully.";
    }
}

package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.entities.Course;
import com.acetutoring.api.mapper.CourseMapper;
import com.acetutoring.api.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }

    @GetMapping("{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("{courseId}")
    public ResponseEntity<CourseDto> updateCourseById(@PathVariable Long courseId, @RequestBody CourseDto courseDto){
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    @DeleteMapping("{courseId}")
    public String deleteCourseById(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return "Course deleted successfully.";
    }
}

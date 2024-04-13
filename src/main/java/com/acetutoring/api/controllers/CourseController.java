package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.exceptions.CourseCodeAlreadyExistsException;
import com.acetutoring.api.services.CourseService;
import com.acetutoring.api.services.FileService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Value("${project.imageBaseUrl}")
    private String imageBaseUrl;

    @PostMapping("/createCourse")
    public ResponseEntity<CourseDto> createCourse(
            @RequestParam("courseCode") String courseCode,
            @RequestParam("courseName") String courseName,
            @RequestParam("description") String description,
            @RequestParam("grade") String grade,
            @RequestParam("imageUrl") String imageUrl,
            @RequestParam("courseImage") MultipartFile courseImage
    ) {

        if (courseService.isCourseCodeExists(courseCode)) {
            throw new CourseCodeAlreadyExistsException("Course code already exists.");
        }

        String courseImageName = null;

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseCode(courseCode);
        courseDto.setCourseName(courseName);
        courseDto.setDescription(description);
        courseDto.setGrade(grade);

        try {
            courseImageName = fileService.uploadImage(path, courseImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        courseDto.setImageUrl(imageBaseUrl + courseImageName);

        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @GetMapping("{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<CourseDto> updateCourseById(
            @PathVariable Long courseId,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("courseName") String courseName,
            @RequestParam("description") String description,
            @RequestParam("grade") String grade,
            @RequestParam("imageUrl") String imageUrl,
            @RequestParam("courseImage") MultipartFile courseImage
    ) {
        String courseImageName = null;
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseCode(courseCode);
        courseDto.setCourseName(courseName);
        courseDto.setDescription(description);
        courseDto.setGrade(grade);
        courseDto.setImageUrl(imageUrl);

        if (imageUrl.isEmpty()) {
            try {
                courseImageName = fileService.uploadImage(path, courseImage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            courseDto.setImageUrl(imageBaseUrl + courseImageName);
        }

        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public String deleteCourseById(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return "Course deleted successfully.";
    }
}

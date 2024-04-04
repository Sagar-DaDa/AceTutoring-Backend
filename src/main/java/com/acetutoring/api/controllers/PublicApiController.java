package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.*;
import com.acetutoring.api.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/public/api")
public class PublicApiController {
    private BlogPostService blogPostService;
    private TutorService tutorService;
    private TestimonialService testimonialService;
    private AvailableCourseService availableCourseService;
    private EnrollmentService enrollmentService;
    private StudentService studentService;
    private UserService userService;
    private CustomerQueryService customerQueryService;

    @GetMapping("/blogPost/{blogPostId}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable Long blogPostId) {
        return ResponseEntity.ok(blogPostService.getBlogPostByIdPublic(blogPostId));
    }

    @GetMapping("/blogPosts")
    public ResponseEntity<List<BlogPostDto>> getAllBlogPosts() {
        return ResponseEntity.ok(blogPostService.getAllBlogPostPublic());
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<TutorDto> getTutorById(@PathVariable Long tutorId) {
        TutorDto foundTutor = tutorService.getTutorById(tutorId);
        return ResponseEntity.ok(foundTutor);
    }

    @GetMapping("/tutors")
    public ResponseEntity<List<TutorDto>> getAllTutors() {
        return ResponseEntity.ok(tutorService.getAllTutors()
        );
    }

    @GetMapping("/testimonials")
    public ResponseEntity<List<TestimonialDto>> getAllTestimonials() {
        return ResponseEntity.ok(testimonialService.getAllTestimonial());
    }

    @GetMapping("/availableCourse/{availableCourseId}")
    public ResponseEntity<AvailableCourseDto> getAvailableCourseById(
            @PathVariable Long availableCourseId) {
        return ResponseEntity.ok(availableCourseService.getAvailableCourseById(availableCourseId));
    }

    @GetMapping("/availableCourses/{category}/{fromGrade}/{toGrade}")
    public ResponseEntity<Map<Long, List<AvailableCourseDto>>> getAllAvailableCourses(
            @PathVariable String category,
            @PathVariable Long fromGrade,
            @PathVariable Long toGrade) {
        Map<Long, List<AvailableCourseDto>> availableCourseMap = new HashMap<>();
        List<AvailableCourseDto> availableCourseDtosList = availableCourseService.getAllAvailableCourses();

        for (AvailableCourseDto availableCourse : availableCourseDtosList) {
            if (availableCourse.getCategory().equalsIgnoreCase(category)
                    && (Long.parseLong(availableCourse.getCourse().getGrade()) >= fromGrade)
                    && (Long.parseLong(availableCourse.getCourse().getGrade()) <= toGrade)) {
                List<AvailableCourseDto> nestedList;
                if (availableCourseMap.containsKey(Long.parseLong(availableCourse.getCourse().getGrade()))) {
                    nestedList = new LinkedList<>();
                    nestedList = availableCourseMap.get(Long.parseLong(availableCourse.getCourse().getGrade()));
                    nestedList.add(availableCourse);
                    availableCourseMap.put(Long.parseLong(availableCourse.getCourse().getGrade()), nestedList);
                } else {
                    nestedList = new LinkedList<>();
                    nestedList.add(availableCourse);
                    availableCourseMap.put(Long.parseLong(availableCourse.getCourse().getGrade()), nestedList);
                }
            }
        }
        return ResponseEntity.ok(availableCourseMap);
    }

    @PostMapping("/enrollment")
    public ResponseEntity<EnrollmentDto> createEnrollment(
            @RequestBody EnrollmentDto enrollmentDto) {
        System.out.println(
                "[CUSTOM] PublicController.java, line-91, enrollmentDto.enrolledCourseId: "
                        + enrollmentDto.getEnrolledCourseId());
        AvailableCourseDto courseDto = availableCourseService
                .getAvailableCourseById(enrollmentDto.getEnrolledCourseId());
        System.out.println("[CUSTOM] PublicController.java, line-96, courseName: " + courseDto.getCourse().getCourseName());
        System.out.println("[CUSTOM] PublicController.java, line-96, duration: " + courseDto.getDuration());
        return ResponseEntity.ok(enrollmentService.createEnrollment(enrollmentDto));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @GetMapping("/checkEmailExists/{emailAddress}")
    public ResponseEntity<Boolean> isEmailExists(@PathVariable String emailAddress) {
        return ResponseEntity.ok((studentService.isStudentExistsWithEmail(emailAddress)
                || userService.isUserExistsWithEmail(emailAddress)));
    }

    @PostMapping("/customerQuery")
    public String createCustomerQuery(@RequestBody CustomerQueryDto customerQueryDto){
        customerQueryService.createCustomerQuery(customerQueryDto);
        return "Customer query created.";
    }

}

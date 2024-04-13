package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.*;
import com.acetutoring.api.services.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@NoArgsConstructor
@RequestMapping("/public/api")
public class PublicApiController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private AvailableCourseService availableCourseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerQueryService customerQueryService;

    @Autowired
    private AuthService authService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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

    @PostMapping("/studentLogin")
    public ResponseEntity<String> studentLogin(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.studentLogin(loginDto), HttpStatus.OK);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<String> adminLogin(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.adminLogin(loginDto), HttpStatus.OK);
    }

    @PostMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable String email){
        authService.resetPassword(email);
        return "Password reset successfully.";
    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImageResource(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getImageResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());;
    }

}

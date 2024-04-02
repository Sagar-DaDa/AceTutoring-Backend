package com.acetutoring.api.public_api;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.dto.BlogPostDto;
import com.acetutoring.api.dto.TestimonialDto;
import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.other_services.EmailSender;
import com.acetutoring.api.services.AvailableCourseService;
import com.acetutoring.api.services.BlogPostService;
import com.acetutoring.api.services.TestimonialService;
import com.acetutoring.api.services.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PublicController {
    private BlogPostService blogPostService;
    private TutorService tutorService;
    private TestimonialService testimonialService;
    private AvailableCourseService availableCourseService;

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
            @PathVariable Long availableCourseId){
        return ResponseEntity.ok(availableCourseService.getAvailableCourseById(availableCourseId));
    }

    @GetMapping("/availableCourses/{category}/{fromGrade}/{toGrade}")
    public ResponseEntity<Map<Long, List<AvailableCourseDto>>> getAllAvailableCourses(
            @PathVariable String category,
            @PathVariable Long fromGrade,
            @PathVariable Long toGrade){
        Map<Long, List<AvailableCourseDto>> availableCourseMap = new HashMap<>();
        List<AvailableCourseDto> availableCourseDtosList= availableCourseService.getAllAvailableCourses();

        for (AvailableCourseDto availableCourse : availableCourseDtosList){
            if(availableCourse.getCategory().equalsIgnoreCase(category)
                    && (Long.parseLong(availableCourse.getCourse().getGrade()) >= fromGrade)
                    && (Long.parseLong(availableCourse.getCourse().getGrade()) <= toGrade)){
                List<AvailableCourseDto> nestedList;
                if(availableCourseMap.containsKey(Long.parseLong(availableCourse.getCourse().getGrade()))){
                    nestedList = new LinkedList<>();
                    nestedList = availableCourseMap.get(Long.parseLong(availableCourse.getCourse().getGrade()));
                    nestedList.add(availableCourse);
                    availableCourseMap.put(Long.parseLong(availableCourse.getCourse().getGrade()), nestedList);
                }else{
                    nestedList = new LinkedList<>();
                    nestedList.add(availableCourse);
                    availableCourseMap.put(Long.parseLong(availableCourse.getCourse().getGrade()), nestedList);
                }
            }
        }
        return ResponseEntity.ok(availableCourseMap);
    }

}

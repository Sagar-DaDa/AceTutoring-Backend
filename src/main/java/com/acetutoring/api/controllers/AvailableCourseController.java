package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.services.AvailableCourseService;
import com.acetutoring.api.services.CourseService;
import com.acetutoring.api.services.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/availableCourses")
public class AvailableCourseController {

    private AvailableCourseService availableCourseService;

    private CourseService courseService;

    private TutorService tutorService;

    @PostMapping("/launchCourse")
    public ResponseEntity<AvailableCourseDto> createAvailableCourse(
            @RequestParam("courseId") String courseId,
            @RequestParam("duration") String duration,
            @RequestParam("category") String category,
            @RequestParam("classDays") String classDays,
            @RequestParam("classStartTime") String classStartTime,
            @RequestParam("classEndTime") String classEndTime,
            @RequestParam("fees") String fees,
            @RequestParam("tutorId") String tutorId
    ){

        AvailableCourseDto availableCourseDto = new AvailableCourseDto();
        availableCourseDto.setDuration(duration);
        availableCourseDto.setCategory(category);
        availableCourseDto.setClassDays(classDays);
        availableCourseDto.setClassStartTime(classStartTime);
        availableCourseDto.setClassEndTime(classEndTime);

        Long tutor = Long.parseLong(tutorId);
        Long course = Long.parseLong(courseId);
        double fee = Double.parseDouble(fees);

        availableCourseDto.setTutor(tutorService.getTutorById(tutor));
        availableCourseDto.setCourse(courseService.getCourseById(course));
        availableCourseDto.setFees(fee);

        return ResponseEntity.ok(availableCourseService.createAvailableCourse(availableCourseDto));
    }

    @GetMapping("{availableCourseId}")
    public ResponseEntity<AvailableCourseDto> getAvailableCourseById(
            @PathVariable Long availableCourseId){
        return ResponseEntity.ok(availableCourseService.getAvailableCourseById(availableCourseId));
    }

    @GetMapping
    public ResponseEntity<List<AvailableCourseDto>> getAllAvailableCourses(){
        return ResponseEntity.ok(availableCourseService.getAllAvailableCourses());
    }

    @PutMapping("{availableCourseId}")
    public ResponseEntity<AvailableCourseDto> updateAvailableCourseById(
            @PathVariable Long availableCourseId, @RequestBody AvailableCourseDto availableCourseDto){
        return ResponseEntity.ok(availableCourseService.updateAvailableCourseById(
                availableCourseId, availableCourseDto
        ));
    }

    @DeleteMapping("/deleteAvailableCourse/{availableCourseId}")
    public String deleteAvailableCourse(@PathVariable Long availableCourseId){
        availableCourseService.deleteAvailableCourseById(availableCourseId);

        return "Course deleted successfully";
    }
}

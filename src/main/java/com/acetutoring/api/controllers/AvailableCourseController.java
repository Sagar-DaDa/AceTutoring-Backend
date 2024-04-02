package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.services.AvailableCourseService;
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

    @PostMapping
    public ResponseEntity<AvailableCourseDto> createAvailableCourse(
            @RequestBody AvailableCourseDto availableCourseDto){
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
}

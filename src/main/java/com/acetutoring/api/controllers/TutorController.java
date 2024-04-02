package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.services.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    private TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorDto> createTutor(@RequestBody TutorDto tutorDto){
        TutorDto savedTutor = tutorService.createTutor(tutorDto);
        return new ResponseEntity<>(savedTutor, HttpStatus.CREATED);
    }

    @GetMapping("{tutorId}")
    public ResponseEntity<TutorDto> getTutorById(@PathVariable Long tutorId){
        TutorDto foundTutor = tutorService.getTutorById(tutorId);
        return ResponseEntity.ok(foundTutor);
    }

    @GetMapping
    public ResponseEntity<List<TutorDto>> getAllTutors(){
        return ResponseEntity.ok(tutorService.getAllTutors());
    }

    @PutMapping("{tutorId}")
    public ResponseEntity<TutorDto> updateTutorById(
            @PathVariable Long tutorId,
            @RequestBody TutorDto updatedTutor){
        return ResponseEntity.ok(tutorService.updateTutor(tutorId, updatedTutor));
    }

    @DeleteMapping("{tutorId}")
    public ResponseEntity<String> deleteTutorById(@PathVariable Long tutorId){
        tutorService.deleteTutor(tutorId);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}

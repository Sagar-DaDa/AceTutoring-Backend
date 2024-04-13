package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.services.FileService;
import com.acetutoring.api.services.TutorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Value("${project.imageBaseUrl}")
    private String imageBaseUrl;

    @PostMapping("/createTutor")
    public ResponseEntity<TutorDto> createTutor(
            @RequestParam("fullName") String fullName,
            @RequestParam("gender") String gender,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("qualifications") String qualifications,
            @RequestParam("experienceInyears") String experienceInyears,
            @RequestParam("specialization") String specialization,
            @RequestParam("workingOrganization") String workingOrganization,
            @RequestParam("teachingPhilosophy") String teachingPhilosophy,
            @RequestParam("tutorImage") MultipartFile tutorImage
    ){
        String tutorImageName = null;

        TutorDto newTutorDto = new TutorDto();

        newTutorDto.setFullName(fullName);
        newTutorDto.setGender(gender);
        newTutorDto.setAddress(address);
        newTutorDto.setEmail(email);
        newTutorDto.setMobile(mobile);
        newTutorDto.setQualifications(qualifications);
        newTutorDto.setExperienceInyears(experienceInyears);
        newTutorDto.setSpecialization(specialization);
        newTutorDto.setWorkingOrganization(workingOrganization);
        newTutorDto.setTeachingPhilosophy(teachingPhilosophy);

        try {
            tutorImageName = fileService.uploadImage(path, tutorImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        newTutorDto.setImageUrl(imageBaseUrl + tutorImageName);

        return new ResponseEntity<>(tutorService.createTutor(newTutorDto), HttpStatus.CREATED);
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

    @PutMapping("/updateTutor/{tutorId}")
    public ResponseEntity<TutorDto> updateTutorById(
            @PathVariable Long tutorId,
            @RequestParam("fullName") String fullName,
            @RequestParam("gender") String gender,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("qualifications") String qualifications,
            @RequestParam("experienceInyears") String experienceInyears,
            @RequestParam("specialization") String specialization,
            @RequestParam("workingOrganization") String workingOrganization,
            @RequestParam("teachingPhilosophy") String teachingPhilosophy,
            @RequestParam("tutorImage") MultipartFile tutorImage
    ){
        String tutorImageName = null;

        TutorDto newTutorDto = new TutorDto();

        newTutorDto.setFullName(fullName);
        newTutorDto.setGender(gender);
        newTutorDto.setAddress(address);
        newTutorDto.setEmail(email);
        newTutorDto.setMobile(mobile);
        newTutorDto.setQualifications(qualifications);
        newTutorDto.setExperienceInyears(experienceInyears);
        newTutorDto.setSpecialization(specialization);
        newTutorDto.setWorkingOrganization(workingOrganization);
        newTutorDto.setTeachingPhilosophy(teachingPhilosophy);

        try {
            tutorImageName = fileService.uploadImage(path, tutorImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        newTutorDto.setImageUrl(imageBaseUrl + tutorImageName);

        return ResponseEntity.ok(tutorService.updateTutor(tutorId, newTutorDto));
    }

    @DeleteMapping("/deleteTutor/{tutorId}")
    public ResponseEntity<String> deleteTutorById(@PathVariable Long tutorId){
        tutorService.deleteTutor(tutorId);
        return ResponseEntity.ok("Tutor deleted successfully.");
    }
}

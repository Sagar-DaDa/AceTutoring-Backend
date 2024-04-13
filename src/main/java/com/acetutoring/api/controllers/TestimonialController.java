package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.TestimonialDto;
import com.acetutoring.api.services.FileService;
import com.acetutoring.api.services.TestimonialService;
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
@RequestMapping("/api/testimonials")
public class TestimonialController {
    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Value("${project.imageBaseUrl}")
    private String imageBaseUrl;

    @PostMapping("/createTestimonial")
    public ResponseEntity<TestimonialDto> createTestimonial(
            @RequestParam("statement") String statement,
            @RequestParam("customerName") String customerName,
            @RequestParam("position") String position,
            @RequestParam("organization") String organization,
            @RequestParam("testimonialImage") MultipartFile testimonialImage
    ){
        String testimonialImageName = null;

        TestimonialDto testimonialDto = new TestimonialDto();

        testimonialDto.setStatement(statement);
        testimonialDto.setCustomerName(customerName);
        testimonialDto.setPosition(position);
        testimonialDto.setOrganization(organization);

        try {
            testimonialImageName = fileService.uploadImage(path, testimonialImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testimonialDto.setImageUrl(imageBaseUrl + testimonialImageName);

        return new ResponseEntity<>(testimonialService.createTestimonial(testimonialDto), HttpStatus.CREATED);
    }

    @GetMapping("{testimonialId}")
    public ResponseEntity<TestimonialDto> getTestimonialById(@PathVariable Long testimonialId){
        return ResponseEntity.ok(testimonialService.getTestimonialById(testimonialId));
    }

    @GetMapping
    public ResponseEntity<List<TestimonialDto>> getAllTestimonials(){
        return ResponseEntity.ok(testimonialService.getAllTestimonial());
    }

    @PutMapping("/updateTestimonial/{testimonialId}")
    public ResponseEntity<TestimonialDto> updateTestimonialById(
            @PathVariable Long testimonialId,
            @RequestParam("statement") String statement,
            @RequestParam("customerName") String customerName,
            @RequestParam("position") String position,
            @RequestParam("organization") String organization,
            @RequestParam("testimonialImage") MultipartFile testimonialImage
    ){
        String testimonialImageName = null;

        TestimonialDto testimonialDto = new TestimonialDto();

        testimonialDto.setStatement(statement);
        testimonialDto.setCustomerName(customerName);
        testimonialDto.setPosition(position);
        testimonialDto.setOrganization(organization);

        try {
            testimonialImageName = fileService.uploadImage(path, testimonialImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testimonialDto.setImageUrl(imageBaseUrl + testimonialImageName);

        return ResponseEntity.ok(testimonialService.updateTestimonial(testimonialId, testimonialDto));
    }

    @DeleteMapping("/deleteTestimonial/{testimonialId}")
    public String deleteTestimonialById(@PathVariable Long testimonialId){
        testimonialService.deleteTestimonial(testimonialId);
        return "Testimonial deleted successfully.";
    }

}

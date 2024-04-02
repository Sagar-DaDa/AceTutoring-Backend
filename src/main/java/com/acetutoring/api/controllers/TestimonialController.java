package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.TestimonialDto;
import com.acetutoring.api.services.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {
    private TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialDto> createTestimonial(
            @RequestBody TestimonialDto testimonialDto){
        return ResponseEntity.ok(testimonialService.createTestimonial(testimonialDto));
    }

    @GetMapping("{testimonialId}")
    public ResponseEntity<TestimonialDto> getTestimonialById(@PathVariable Long testimonialId){
        return ResponseEntity.ok(testimonialService.getTestimonialById(testimonialId));
    }

    @GetMapping
    public ResponseEntity<List<TestimonialDto>> getAllTestimonials(){
        return ResponseEntity.ok(testimonialService.getAllTestimonial());
    }

    @PutMapping("{testimonialId}")
    public ResponseEntity<TestimonialDto> updateTestimonialById(
            @PathVariable Long testimonialId, @RequestBody TestimonialDto testimonialDto){
        return ResponseEntity.ok(testimonialService.updateTestimonial(testimonialId, testimonialDto));
    }

    @DeleteMapping("{testimonialId}")
    public String deleteTestimonialById(@PathVariable Long testimonialId){
        testimonialService.deleteTestimonial(testimonialId);
        return "Testimonial deleted successfully.";
    }

}

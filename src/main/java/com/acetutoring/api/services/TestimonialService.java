package com.acetutoring.api.services;

import com.acetutoring.api.dto.TestimonialDto;

import java.util.List;

public interface TestimonialService {
    TestimonialDto createTestimonial(TestimonialDto testimonialDto);

    TestimonialDto getTestimonialById(Long testimonialId);

    List<TestimonialDto> getAllTestimonial();

    TestimonialDto updateTestimonial(Long testimonialId, TestimonialDto testimonialDto);

    void deleteTestimonial(Long testimonialId);
}

package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.TestimonialDto;
import com.acetutoring.api.entities.Testimonial;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.TestimonialMapper;
import com.acetutoring.api.repositories.TestimonialRepo;
import com.acetutoring.api.services.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {
    private TestimonialRepo testimonialRepo;

    @Override
    public TestimonialDto createTestimonial(TestimonialDto testimonialDto) {
        return TestimonialMapper.mapToTestimonialDto(
                testimonialRepo
                        .save(TestimonialMapper.mapToTestimonial(testimonialDto))
        );
    }

    @Override
    public TestimonialDto getTestimonialById(Long testimonialId) {
        return TestimonialMapper.mapToTestimonialDto(
                testimonialRepo.findById(testimonialId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Testimonial not found. Invalid testimonial ID: " + testimonialId
                                )
                        )
        );
    }

    @Override
    public List<TestimonialDto> getAllTestimonial() {
        return testimonialRepo
                .findAll()
                .stream()
                .map(TestimonialMapper::mapToTestimonialDto)
                .toList();
    }

    @Override
    public TestimonialDto updateTestimonial(Long testimonialId, TestimonialDto testimonialDto) {
        Testimonial foundTestimonial = testimonialRepo
                .findById(testimonialId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "\"Testimonial not found. Invalid testimonial ID: \" + testimonialId"
                ));
        foundTestimonial.setStatement(testimonialDto.getStatement());
        foundTestimonial.setCustomerName(testimonialDto.getCustomerName());
        foundTestimonial.setOrganization(testimonialDto.getOrganization());
        foundTestimonial.setPosition(testimonialDto.getPosition());
        foundTestimonial.setImageUrl(testimonialDto.getImageUrl());
        return TestimonialMapper.mapToTestimonialDto(testimonialRepo.save(foundTestimonial));
    }

    @Override
    public void deleteTestimonial(Long testimonialId) {
        Testimonial foundTestimonial = testimonialRepo
                .findById(testimonialId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "\"Testimonial not found. Invalid testimonial ID: \" + testimonialId"
                ));
        testimonialRepo.delete(foundTestimonial);
    }
}

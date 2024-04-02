package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.TestimonialDto;
import com.acetutoring.api.entities.Testimonial;

public class TestimonialMapper {
    public static TestimonialDto mapToTestimonialDto(Testimonial testimonial){
        return new TestimonialDto(
                testimonial.getId(),
                testimonial.getStatement(),
                testimonial.getCustomerName(),
                testimonial.getPosition(),
                testimonial.getOrganization(),
                testimonial.getImageUrl(),
                testimonial.getCreatedAt()
        );
    }

    public static Testimonial mapToTestimonial(TestimonialDto testimonialDto){
        return new Testimonial(
                testimonialDto.getId(),
                testimonialDto.getStatement(),
                testimonialDto.getCustomerName(),
                testimonialDto.getPosition(),
                testimonialDto.getOrganization(),
                testimonialDto.getImageUrl(),
                testimonialDto.getCreatedAt()
        );
    }
}

package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepo extends JpaRepository<Testimonial, Long> {
}

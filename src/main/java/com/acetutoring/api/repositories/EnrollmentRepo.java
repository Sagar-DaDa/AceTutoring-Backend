package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
}

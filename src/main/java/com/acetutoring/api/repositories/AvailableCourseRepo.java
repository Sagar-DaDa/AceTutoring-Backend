package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.AvailableCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableCourseRepo extends JpaRepository<AvailableCourse, Long> {
}

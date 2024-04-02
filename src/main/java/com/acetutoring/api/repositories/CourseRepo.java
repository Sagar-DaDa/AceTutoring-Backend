package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}

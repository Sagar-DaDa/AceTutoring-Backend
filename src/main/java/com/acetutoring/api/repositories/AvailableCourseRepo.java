package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.AvailableCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvailableCourseRepo extends JpaRepository<AvailableCourse, Long> {
    @Query(value = "SELECT available_courses.*, COUNT(enrollments.id) AS enrollment_count " +
            "FROM available_courses " +
            "LEFT JOIN enrollments ON enrollments.enrolled_course = available_courses.id " +
            "GROUP BY available_courses.id " +
            "ORDER BY enrollment_count DESC " +
            "LIMIT ?1", nativeQuery = true)
    List<Object[]> findPopularCoursesWithEnrollmentCountWithLimit(int limit);
}

package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
    @Query(value =
            "SELECT CASE WHEN COUNT(*) > 0 " +
                    "THEN TRUE ELSE FALSE " +
                    "END FROM enrollments e " +
                    "WHERE e.enrolled_student = :studentId " +
                    "AND e.active = true", nativeQuery = true)
    Long hasActiveEnrollment(@Param("studentId") Long studentId);
}

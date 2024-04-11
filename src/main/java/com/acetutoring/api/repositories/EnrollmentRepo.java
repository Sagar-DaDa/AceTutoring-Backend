package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
    @Query(value =
            "SELECT CASE WHEN COUNT(*) > 0 " +
                    "THEN TRUE ELSE FALSE " +
                    "END FROM enrollments e " +
                    "WHERE e.enrolled_student = :studentId " +
                    "AND e.active = true", nativeQuery = true)
    Long hasActiveEnrollment(@Param("studentId") Long studentId);

    @Query(value =
            "SELECT CASE WHEN COUNT(*) > 0 " +
                    "THEN TRUE ELSE FALSE " +
                    "END FROM enrollments e " +
                    "WHERE e.enrolled_student = :studentId " +
                    "AND e.finished = true",
            nativeQuery = true
    )
    Long hasFinishedEnrollment(@Param("studentId") Long studentId);

    @Query(value =
            "SELECT e.id, e.enrolledCourse,e.courseStartDate, e.courseEndDate, e.active, e.finished " +
                    "FROM Enrollment e " +
                    "WHERE e.enrolledStudent.id = :studentId")
    List<Object[]> findAllEnrollmentsByStudentId(@Param("studentId") Long studentId);

    Long countByActiveTrue();

    Long countByActiveFalse();

    @Query(value =
            """
                    SELECT\s
                      CAST(date_range.date AS DATE) AS created_date,
                      COUNT(e.created_at) AS enrollment_count\s
                    FROM (
                      SELECT '2024-04-01' + INTERVAL n DAY AS date
                      FROM (
                        SELECT t0.n + t1.n * 10 + t2.n * 100 AS n
                        FROM (
                          SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\s
                          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9
                        ) AS t0
                        CROSS JOIN (
                          SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\s
                          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9
                        ) AS t1
                        CROSS JOIN (
                          SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\s
                          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9
                        ) AS t2
                      ) AS numbers
                      WHERE :startDate + INTERVAL n DAY BETWEEN :startDate AND :endDate
                    ) AS date_range
                    LEFT JOIN enrollments e ON DATE(e.created_at) = date_range.date
                    GROUP BY created_date
                    """,
            nativeQuery = true
    )
    List<Object[]> countEnrollmentsByDateRange(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(value =
                    """
                     SELECT ac.category, COUNT(e.id) totalCount
                     FROM enrollments e
                     RIGHT JOIN available_courses ac ON e.`enrolled_course`=ac.id
                     GROUP BY ac.category;
                    """,
            nativeQuery = true)
    List<Object[]> countEnrollmentsByCategory();

}

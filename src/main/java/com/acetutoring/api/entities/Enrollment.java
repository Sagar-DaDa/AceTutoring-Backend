package com.acetutoring.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long enrolledCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrolled_course")
    private AvailableCourse enrolledCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrolled_student")
    private Student enrolledStudent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "course_start_date")
    private Date courseStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "course_end_date")
    private Date courseEndDate;

    private boolean active = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Enrollment(
            Long id,
            AvailableCourse enrolledCourse,
            Student enrolledStudent,
            Date courseStartDate,
            Date courseEndDate,
            boolean active,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.enrolledCourse = enrolledCourse;
        this.enrolledStudent = enrolledStudent;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

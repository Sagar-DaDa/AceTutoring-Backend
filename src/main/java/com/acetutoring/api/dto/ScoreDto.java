package com.acetutoring.api.dto;

import com.acetutoring.api.entities.Course;
import com.acetutoring.api.entities.Student;
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
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {
    private Long id;
    private StudentDto student;
    private CourseDto course;
    private int totalQuestions;
    private int correctAnswered;
    private Date createdAt;
    private Date updatedAt;
}

package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.Course;
import com.acetutoring.api.entities.Score;
import com.acetutoring.api.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findByStudentAndCourse(Student student, Course course);
}

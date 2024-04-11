package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.ScoreDto;
import com.acetutoring.api.entities.Course;
import com.acetutoring.api.entities.Score;
import com.acetutoring.api.entities.Student;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.ScoreMapper;
import com.acetutoring.api.repositories.CourseRepo;
import com.acetutoring.api.repositories.ScoreRepo;
import com.acetutoring.api.repositories.StudentRepo;
import com.acetutoring.api.services.ScoreService;
import com.acetutoring.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private ScoreRepo scoreRepo;
    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    @Override
    public ScoreDto getScoreById(Long scoreId) {
        Score foundScore = scoreRepo.findById(scoreId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Score not found. Invalid score ID:" + scoreId
                )
        );
        return ScoreMapper.mapToScoreDto(foundScore);
    }

    @Override
    public ScoreDto createScore(ScoreDto scoreDto) {
        return ScoreMapper.mapToScoreDto(scoreRepo.save(ScoreMapper.mapToScore(scoreDto)));
    }

    @Override
    public List<ScoreDto> getAllScores() {
        return scoreRepo.findAll()
                .stream()
                .map(ScoreMapper::mapToScoreDto)
                .toList();
    }

    @Override
    public List<ScoreDto> getAllScoresByStudentId(Long studentId) {
        return List.of();
    }

    @Override
    public List<ScoreDto> getAllScoresByStudentIdAndCourseId(Long studentId, Long courseId) {
        Student foundStudent = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student not found. Invalid student ID: " + studentId
                )
        );

        Course foundCourse = courseRepo.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Course not found. Invalid course ID: " + courseId
                )
        );

        return scoreRepo.findByStudentAndCourse(
                        foundStudent,
                        foundCourse)
                .stream()
                .map(ScoreMapper::mapToScoreDto)
                .toList();
    }

    @Override
    public List<ScoreDto> getLatestAllCoursesScoreByStudentId(Long studentId) {
        return List.of();
    }

    @Override
    public ScoreDto getLatestScoreByStudentIdAndCourseId(Long studentId) {
        return null;
    }
}

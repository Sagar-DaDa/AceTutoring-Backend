package com.acetutoring.api.services;

import com.acetutoring.api.dto.ScoreDto;

import java.util.List;

public interface ScoreService {
    ScoreDto getScoreById(Long scoreId);

    ScoreDto createScore(ScoreDto scoreDto);

    List<ScoreDto> getAllScores();

    List<ScoreDto> getAllScoresByStudentId(Long studentId);

    List<ScoreDto> getAllScoresByStudentIdAndCourseId(Long studentId, Long courseId);

    List<ScoreDto> getLatestAllCoursesScoreByStudentId(Long studentId);

    ScoreDto getLatestScoreByStudentIdAndCourseId(Long studentId);






}

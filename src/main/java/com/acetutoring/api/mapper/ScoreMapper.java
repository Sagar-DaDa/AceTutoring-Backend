package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.ScoreDto;
import com.acetutoring.api.entities.Score;

public class ScoreMapper {
    public static ScoreDto mapToScoreDto(Score score){
        return new ScoreDto(
                score.getId(),
                StudentMapper.mapToStudentDto(score.getStudent()),
                CourseMapper.mapToCourseDto(score.getCourse()),
                score.getTotalQuestions(),
                score.getCorrectAnswered(),
                score.getCreatedAt(),
                score.getUpdatedAt()
        );
    }

    public static Score mapToScore(ScoreDto scoreDto){
        return new Score(
                scoreDto.getId(),
                StudentMapper.mapToStudent(scoreDto.getStudent()),
                CourseMapper.mapToCourse(scoreDto.getCourse()),
                scoreDto.getTotalQuestions(),
                scoreDto.getCorrectAnswered(),
                scoreDto.getCreatedAt(),
                scoreDto.getUpdatedAt()
        );
    }
}

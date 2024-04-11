package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.QuestionDto;
import com.acetutoring.api.entities.Question;
import lombok.AllArgsConstructor;

public class QuestionMapper {
    public static QuestionDto mapToQuestionDto(Question question){
        return new QuestionDto(
                question.getId(),
                question.getCourse(),
                question.getQuestion(),
                question.getOptions(),
                question.getAnswer(),
                question.isPublished(),
                question.getCreatedBy(),
                question.getCreatedAt(),
                question.getUpdatedAt()
        );
    }

    public static Question mapToQuestion(QuestionDto questionDto){
        return new Question(
                questionDto.getId(),
                questionDto.getCourse(),
                questionDto.getQuestion(),
                questionDto.getOptions(),
                questionDto.getAnswer(),
                questionDto.isPublished(),
                questionDto.getCreatedBy(),
                questionDto.getCreatedAt(),
                questionDto.getUpdatedAt()
        );
    }
}

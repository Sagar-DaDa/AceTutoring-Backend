package com.acetutoring.api.services;

import com.acetutoring.api.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto getQuestionById(Long questionId);

    QuestionDto createQuestion(QuestionDto questionDto);

    List<QuestionDto> getAllQuestions();

    List<QuestionDto> getAllQuestionsByCourseId(Long courseId);

    List<QuestionDto> getLatestQuestionsByCourseId(Long courseId);

    QuestionDto updateQuestionById(Long questionId, QuestionDto questionDto);

    QuestionDto publishQuestionByQuestionId(Long questionId);

    QuestionDto unpublishQuestionByQuestionId(Long questionId);

    void deleteQuestionById(Long questionId);


}

package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.QuestionDto;
import com.acetutoring.api.entities.Question;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.QuestionMapper;
import com.acetutoring.api.repositories.QuestionRepo;
import com.acetutoring.api.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepo questionRepo;

    @Override
    public QuestionDto getQuestionById(Long questionId) {
        return null;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        return QuestionMapper.mapToQuestionDto(
                questionRepo.save(QuestionMapper.mapToQuestion(questionDto))
        );
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return List.of();
    }

    @Override
    public List<QuestionDto> getAllQuestionsByCourseId(Long courseId) {
        return questionRepo
                .findAll()
                .stream()
                .map(QuestionMapper::mapToQuestionDto)
                .toList();
    }

    @Override
    public List<QuestionDto> getLatestQuestionsByCourseId(Long courseId) {
        return null;
    }

    @Override
    public QuestionDto updateQuestionById(Long questionId, QuestionDto questionDto) {
        return null;
    }

    @Override
    public QuestionDto publishQuestionByQuestionId(Long questionId) {
        Question foundQuestion = questionRepo.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question not found. Invalid question ID: " + questionId)
        );

        foundQuestion.setPublished(true);

        return QuestionMapper.mapToQuestionDto(questionRepo.save(foundQuestion));
    }

    @Override
    public QuestionDto unpublishQuestionByQuestionId(Long questionId) {
        Question foundQuestion = questionRepo.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question not found. Invalid question ID: " + questionId)
        );

        foundQuestion.setPublished(false);

        return QuestionMapper.mapToQuestionDto(questionRepo.save(foundQuestion));
    }

    @Override
    public void deleteQuestionById(Long questionId) {

    }
}

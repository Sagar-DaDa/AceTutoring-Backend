package com.acetutoring.api.services;

import com.acetutoring.api.dto.TutorDto;

import java.util.List;

public interface TutorService {
     TutorDto createTutor(TutorDto tutorDto);

     TutorDto getTutorById(Long tutorId);

     List<TutorDto> getAllTutors();

     TutorDto updateTutor(Long tutorId, TutorDto tutorDto);

     void deleteTutor(Long tutorId);

     Long totalTutorCount();
}

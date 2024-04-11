package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.entities.Tutor;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.TutorMapper;
import com.acetutoring.api.repositories.TutorRepo;
import com.acetutoring.api.services.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TutorServiceImpl implements TutorService {

    private TutorRepo tutorRepo;

    @Override
    public TutorDto createTutor(TutorDto tutorDto) {
        Tutor tutor = TutorMapper.mapToTutor(tutorDto);
        Tutor savedTutor = tutorRepo.save(tutor);
        return TutorMapper.mapToTutorDto(savedTutor);
    }

    @Override
    public TutorDto getTutorById(Long tutorId) {
        Tutor tutor = tutorRepo.findById(tutorId).orElseThrow(() ->
                new ResourceNotFoundException("Tutor not found. Invalid tutor ID: " + tutorId));

        return TutorMapper.mapToTutorDto(tutor);
    }

    @Override
    public List<TutorDto> getAllTutors() {
        List<Tutor> allTutorList = tutorRepo.findAll();
        return allTutorList.stream().map(TutorMapper::mapToTutorDto).collect(Collectors.toList());
    }

    @Override
    public TutorDto updateTutor(Long tutorId, TutorDto tutorDto) {
        Tutor foundTutor = tutorRepo.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid tutor id."));
        foundTutor.setFullName(tutorDto.getFullName());
        foundTutor.setAddress(tutorDto.getAddress());
        foundTutor.setEmail(tutorDto.getEmail());
        foundTutor.setMobile(tutorDto.getMobile());
        foundTutor.setQualifications(tutorDto.getQualifications());
        foundTutor.setExperienceInyears(tutorDto.getExperienceInyears());
        foundTutor.setSpecialization(tutorDto.getSpecialization());
        foundTutor.setWorkingOrganization(tutorDto.getWorkingOrganization());
        foundTutor.setTeachingPhilosophy(tutorDto.getTeachingPhilosophy());
        foundTutor.setImageUrl(tutorDto.getImageUrl());

        return TutorMapper.mapToTutorDto(tutorRepo.save(foundTutor));
    }

    @Override
    public void deleteTutor(Long tutorId) {
        Tutor foundTutor = tutorRepo.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found. Invalid tutor id."));
        tutorRepo.deleteById(tutorId);
    }

    @Override
    public Long totalTutorCount() {
        return tutorRepo.count();
    }


}

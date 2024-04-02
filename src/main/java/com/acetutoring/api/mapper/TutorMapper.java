package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.TutorDto;
import com.acetutoring.api.entities.Tutor;

public class TutorMapper {

    public static TutorDto mapToTutorDto(Tutor tutor){
        return new TutorDto(
                tutor.getId(),
                tutor.getFullName(),
                tutor.getGender(),
                tutor.getAddress(),
                tutor.getEmail(),
                tutor.getMobile(),
                tutor.getQualifications(),
                tutor.getExperienceInyears(),
                tutor.getSpecialization(),
                tutor.getWorkingOrganization(),
                tutor.getTeachingPhilosophy(),
                tutor.getImageUrl(),
                tutor.getCreatedAt(),
                tutor.getUpdatedAt()
        );
    }

    public static Tutor mapToTutor(TutorDto tutorDto){
        return new Tutor(
                tutorDto.getId(),
                tutorDto.getFullName(),
                tutorDto.getGender(),
                tutorDto.getAddress(),
                tutorDto.getEmail(),
                tutorDto.getMobile(),
                tutorDto.getQualifications(),
                tutorDto.getExperienceInyears(),
                tutorDto.getSpecialization(),
                tutorDto.getWorkingOrganization(),
                tutorDto.getTeachingPhilosophy(),
                tutorDto.getImageUrl(),
                tutorDto.getCreatedAt(),
                tutorDto.getUpdatedAt()
        );
    }
}

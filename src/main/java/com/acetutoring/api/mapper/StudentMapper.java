package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.entities.Student;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getStudentName(),
                student.getParentName(),
                student.getContactNumber(),
                student.getEmail(),
                student.getSchool(),
                student.getAdditionalInformation(),
                UserMapper.mapToUserDto(student.getUserId()),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }

    public static Student mapToStudent(StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getStudentName(),
                studentDto.getParentName(),
                studentDto.getContactNumber(),
                studentDto.getEmail(),
                studentDto.getSchool(),
                studentDto.getAdditionalInformation(),
                UserMapper.mapToUser(studentDto.getUserId()),
                studentDto.getCreatedAt(),
                studentDto.getUpdatedAt()
        );
    }
}

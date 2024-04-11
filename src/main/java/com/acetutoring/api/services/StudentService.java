package com.acetutoring.api.services;

import com.acetutoring.api.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudentById(Long studentId, StudentDto studentDto);

    void deleteStudentById(Long studentId);

    boolean isStudentExistsWithId(Long studentId);

    boolean isStudentExistsWithEmail(String email);

    Long totalStudentCount();


}

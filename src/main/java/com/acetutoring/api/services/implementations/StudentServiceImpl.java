package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.entities.Student;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.StudentMapper;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.repositories.StudentRepo;
import com.acetutoring.api.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        return StudentMapper.mapToStudentDto(studentRepo.save(StudentMapper.mapToStudent(studentDto)));
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return StudentMapper.mapToStudentDto(studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student not found. Invalid student ID: " + studentId
                )
        ));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepo
                .findAll()
                .stream()
                .map(StudentMapper::mapToStudentDto)
                .toList();
    }

    @Override
    public StudentDto updateStudentById(Long studentId, StudentDto studentDto) {
        Student foundStudent = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student not found. Invalid student ID: " + studentId
                )
        );
        foundStudent.setStudentName(studentDto.getStudentName());
        foundStudent.setParentName(studentDto.getParentName());
        foundStudent.setContactNumber(studentDto.getContactNumber());
        foundStudent.setEmail(studentDto.getEmail());
        foundStudent.setSchool(studentDto.getSchool());
        foundStudent.setAdditionalInformation(studentDto.getAdditionalInformation());
        foundStudent.setUserId(UserMapper.mapToUser(studentDto.getUserId()));

        return StudentMapper.mapToStudentDto(studentRepo.save(foundStudent));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student foundStudent = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Student not found. Invalid student ID: " + studentId
                )
        );
        studentRepo.delete(foundStudent);
    }

    @Override
    public boolean isStudentExistsWithId(Long studentId) {
        return studentRepo.findById(studentId).isPresent();
    }

    @Override
    public boolean isStudentExistsWithEmail(String email) {
        return studentRepo.findByEmail(email).isPresent();
    }
}

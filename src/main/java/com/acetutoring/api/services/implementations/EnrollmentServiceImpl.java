package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.EnrollmentDto;
import com.acetutoring.api.dto.StudentDto;
import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.Enrollment;
import com.acetutoring.api.exceptions.EmailAlreadyExistsException;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.AvailableCourseMapper;
import com.acetutoring.api.mapper.EnrollmentMapper;
import com.acetutoring.api.mapper.StudentMapper;
import com.acetutoring.api.other_services.EmailSender;
import com.acetutoring.api.other_services.PasswordGenerator;
import com.acetutoring.api.repositories.EnrollmentRepo;
import com.acetutoring.api.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private EnrollmentRepo enrollmentRepo;
    private StudentService studentService;
    private UserService userService;
    private UserRoleService userRoleService;
    private AvailableCourseService availableCourseService;

    @Autowired
    private EmailSender emailSender;

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto) {
        EnrollmentDto newEnrollment;
        String password;
        UserDto newUser;
        StudentDto newStudent;
        String requestedEmail = enrollmentDto.getEnrolledStudent().getEmail();
        Enrollment newEnrolledStudent;

        if (studentService.isStudentExistsWithEmail(requestedEmail)) {
            throw new EmailAlreadyExistsException("Provided email is associated with another account.");
        } else {
            password = PasswordGenerator.generatePassword();

            newUser = userService.createUser(
                    new UserDto(
                            enrollmentDto.getEnrolledStudent().getStudentName(),
                            enrollmentDto.getEnrolledStudent().getEmail(),
                            enrollmentDto.getEnrolledStudent().getEmail(),
                            enrollmentDto.getEnrolledStudent().getContactNumber(),
                            password,
                            userRoleService.getUserRoleByRoleName("Customer")
                    )); //User created
            newStudent = enrollmentDto.getEnrolledStudent();
            newStudent.setUserId(newUser);

            newEnrollment = new EnrollmentDto();

            newEnrollment.setEnrolledCourse(
                    availableCourseService.getAvailableCourseById(enrollmentDto.getEnrolledCourseId())
            );
            newEnrollment.setEnrolledStudent(studentService.createStudent(newStudent));
              newEnrolledStudent = enrollmentRepo
                      .save(EnrollmentMapper.mapToEnrollment(newEnrollment)); //Enrollment created
        }

        String subject = "Welcome to Ace Tutoring";
        String body = "Dear User,\n\n" +
                "Thank you for registering on Ace Tutoring. We are excited to have you on board!\n\n" +
                "Your login details are as below:\n" +
                "username: " + requestedEmail + "\n" +
                "password: " + password + "\n\n" +
                "Please login to your account and confirm you enrollment.\n\n\n\n" +
                "Best regards,\n" +
                "Ace Tutoring";

        emailSender.sendMail(requestedEmail, subject, body); //Email sent

        return EnrollmentMapper.mapToEnrollmentDto(newEnrolledStudent);
    }

    @Override
    public EnrollmentDto getEnrollmentById(Long enrollmentId) {
        return EnrollmentMapper.mapToEnrollmentDto(
                enrollmentRepo.findById(enrollmentId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Enrollment not found. Invalid enrollment ID: " + enrollmentId
                                )
                        )
        );
    }

    @Override
    public List<EnrollmentDto> getAllEnrollment() {
        return enrollmentRepo
                .findAll()
                .stream()
                .map(EnrollmentMapper::mapToEnrollmentDto)
                .toList();
    }

    @Override
    public EnrollmentDto updateEnrollmentById(Long enrollmentId, EnrollmentDto enrollmentDto) {
        Enrollment foundEnrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Enrollment not found. Invalid enrollment ID: " + enrollmentId
                        )
                );
        foundEnrollment.setEnrolledCourse(
                AvailableCourseMapper.mapToAvailableCourse(enrollmentDto.getEnrolledCourse()));
        foundEnrollment.setEnrolledStudent(
                StudentMapper.mapToStudent(enrollmentDto.getEnrolledStudent()));
        foundEnrollment.setCourseStartDate(enrollmentDto.getCourseStartDate());
        foundEnrollment.setCourseEndDate(enrollmentDto.getCourseEndDate());
        foundEnrollment.setActive(enrollmentDto.isActive());

        return EnrollmentMapper.mapToEnrollmentDto(enrollmentRepo.save(foundEnrollment));
    }

    @Override
    public void deleteEnrollmentById(Long enrollmentId) {
        Enrollment foundEnrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Enrollment not found. Invalid enrollment ID: " + enrollmentId
                        )
                );
        enrollmentRepo.delete(foundEnrollment);
    }

    @Override
    public Long hasActiveEnrollment(Long studentId) {
        return enrollmentRepo.hasActiveEnrollment(studentId);
    }
}

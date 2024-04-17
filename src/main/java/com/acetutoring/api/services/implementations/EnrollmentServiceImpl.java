package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.*;
import com.acetutoring.api.entities.Enrollment;
import com.acetutoring.api.exceptions.EmailAlreadyExistsException;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.AvailableCourseMapper;
import com.acetutoring.api.mapper.EnrollmentMapper;
import com.acetutoring.api.mapper.StudentMapper;
import com.acetutoring.api.utils.EmailSender;
import com.acetutoring.api.utils.PasswordGenerator;
import com.acetutoring.api.repositories.EnrollmentRepo;
import com.acetutoring.api.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
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

            newUser = userService.createCustomer(
                    new UserDto(
                            enrollmentDto.getEnrolledStudent().getStudentName(),
                            enrollmentDto.getEnrolledStudent().getEmail(),
                            enrollmentDto.getEnrolledStudent().getEmail(),
                            enrollmentDto.getEnrolledStudent().getContactNumber(),
                            password
                    )); //User created
            newStudent = enrollmentDto.getEnrolledStudent();
            newStudent.setUserId(newUser);

            newEnrollment = new EnrollmentDto();

            newEnrollment.setEnrolledCourse(
                    availableCourseService.getAvailableCourseById(enrollmentDto.getEnrolledCourseId())
            );
            newEnrollment.setEnrolledStudent(studentService.createStudent(newStudent)); //Student created
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
    public List<EnrollmentDto> getAllEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepo.findAllEnrollmentsByStudentId(studentId)
                .stream()
                .map(EnrollmentMapper::mapObjectToEnrollmentDto)
                .toList();
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
        foundEnrollment.setFinished(enrollmentDto.isFinished());

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

    @Override
    public Long hasFinishedEnrollment(Long studentId) {
        return enrollmentRepo.hasFinishedEnrollment(studentId);
    }

    @Override
    @Scheduled(fixedRate = 86400000)
    public void checkAndUpdateEnrollments() {
        List<Enrollment> enrollments = enrollmentRepo.findAll();
        Date currentDate = new Date();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseEndDate() != null && currentDate.after(enrollment.getCourseEndDate())) {
                enrollment.setActive(false);
                enrollment.setFinished(true);
                enrollmentRepo.save(enrollment);
            }
        }
    }

    @Override
    public void confirmEnrollment(Long enrollmentId, ConfirmEnrollmentDto confirmEnrollmentDto) {
        Enrollment foundEnrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Enrollment not found. Invalid enrollment ID: " + enrollmentId
                        )
                );

        if(confirmEnrollmentDto.getStartDate().after(new Date())){
            foundEnrollment.setCourseStartDate(confirmEnrollmentDto.getStartDate());
            foundEnrollment.setCourseEndDate(confirmEnrollmentDto.getEndDate());
            foundEnrollment.setActive(true);
            enrollmentRepo.save(foundEnrollment);
        }
    }

    @Override
    public void createdNewEnrollmentForExistingStudent(
            EnrollmentForExistingStudentDto enrollmentForExistingStudentDto) {

        StudentDto foundStudent = studentService.getStudentById(enrollmentForExistingStudentDto.getStudentId());
        AvailableCourseDto foundAvailableCourse = availableCourseService
                .getAvailableCourseById(enrollmentForExistingStudentDto.getAvailableCourseId());

        EnrollmentDto newEnrollment = new EnrollmentDto();

        newEnrollment.setEnrolledStudent(foundStudent);
        newEnrollment.setEnrolledCourse(foundAvailableCourse);
        newEnrollment.setCourseStartDate(enrollmentForExistingStudentDto.getCourseStartDate());
        newEnrollment.setCourseEndDate(enrollmentForExistingStudentDto.getCourseEndDate());
        newEnrollment.setActive(true);

        Enrollment enrollment = enrollmentRepo.save(EnrollmentMapper.mapToEnrollment(newEnrollment));

        String subject = "New Course Enrolled";
        String body = "Dear User,\n\n" +
                "You have enrolled new course!\n\n" +
                "Enrollment details are as below:\n" +
                "Course: " + foundAvailableCourse.getCourse().getCourseName() + "\n" +
                "Grade: " + foundAvailableCourse.getCourse().getGrade() + "\n" +
                "Duration: " + foundAvailableCourse.getDuration() + "\n" +
                "Class days: " + foundAvailableCourse.getClassDays() + "\n" +
                "Class start time: " + foundAvailableCourse.getClassStartTime() + "\n" +
                "Class end time: " + foundAvailableCourse.getClassEndTime() + "\n" +
                "Class start date: " + newEnrollment.getCourseStartDate() + "\n" +
                "Class end date: " + newEnrollment.getCourseEndDate() + "\n\n" +
                "Please login to your account for more information.\n\n\n\n" +
                "Best regards,\n" +
                "Ace Tutoring";

        emailSender.sendMail(foundStudent.getEmail(), subject, body);
    }

    @Override
    public Long totalActiveEnrollmentsCount() {
        return enrollmentRepo.countByActiveTrue();
    }

    @Override
    public Long totalInactiveEnrollmentsCount() {
        return enrollmentRepo.countByActiveFalse();
    }

    @Override
    public List<Object[]> getEnrollmentsByDateRange(Date startDate, Date endDate) {
        return enrollmentRepo.countEnrollmentsByDateRange(startDate, endDate);
    }

    @Override
    public List<Object[]> getEnrollmentsByCategory() {
        return enrollmentRepo.countEnrollmentsByCategory();
    }
}

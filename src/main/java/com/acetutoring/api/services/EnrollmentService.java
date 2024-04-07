package com.acetutoring.api.services;

import com.acetutoring.api.dto.ConfirmEnrollmentDto;
import com.acetutoring.api.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);

    EnrollmentDto getEnrollmentById(Long enrollmentId);

    List<EnrollmentDto> getAllEnrollmentsByStudentId(Long studentId);

    List<EnrollmentDto> getAllEnrollment();

    EnrollmentDto updateEnrollmentById(Long enrollmentId, EnrollmentDto enrollmentDto);

    void deleteEnrollmentById(Long enrollmentId);

    Long hasActiveEnrollment(Long studentId);

    Long hasFinishedEnrollment(Long studentId);

    void checkAndUpdateEnrollments();

    void confirmEnrollment(
            Long enrollmentId,
            ConfirmEnrollmentDto confirmEnrollmentDto);
}

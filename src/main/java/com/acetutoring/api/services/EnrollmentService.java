package com.acetutoring.api.services;

import com.acetutoring.api.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto createEnrollment(EnrollmentDto enrollmentDto);

    EnrollmentDto getEnrollmentById(Long enrollmentId);

    List<EnrollmentDto> getAllEnrollment();

    EnrollmentDto updateEnrollmentById(Long enrollmentId, EnrollmentDto enrollmentDto);

    void deleteEnrollmentById(Long enrollmentId);
}

package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.AvailableCourseDto;
import com.acetutoring.api.dto.EnrollmentDto;
import com.acetutoring.api.entities.AvailableCourse;
import com.acetutoring.api.entities.Enrollment;

public class EnrollmentMapper {
    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment){
        return new EnrollmentDto(
                enrollment.getId(),
                AvailableCourseMapper.mapToAvailableCourseDto(enrollment.getEnrolledCourse()),
                StudentMapper.mapToStudentDto(enrollment.getEnrolledStudent()),
                enrollment.getCourseStartDate(),
                enrollment.getCourseEndDate(),
                enrollment.isActive(),
                enrollment.isFinished(),
                enrollment.getCreatedAt(),
                enrollment.getUpdatedAt()
        );
    }

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto){
        return new Enrollment(
                enrollmentDto.getId(),
                AvailableCourseMapper.mapToAvailableCourse(enrollmentDto.getEnrolledCourse()),
                StudentMapper.mapToStudent(enrollmentDto.getEnrolledStudent()),
                enrollmentDto.getCourseStartDate(),
                enrollmentDto.getCourseEndDate(),
                enrollmentDto.isActive(),
                enrollmentDto.isFinished(),
                enrollmentDto.getCreatedAt(),
                enrollmentDto.getUpdatedAt()
        );
    }

    public static EnrollmentDto mapObjectToEnrollmentDto(Object[] object){
        return new EnrollmentDto(
                (Long) object[0],
                AvailableCourseMapper.mapToAvailableCourseDto((AvailableCourse) object[1]),
                (Boolean) object[2],
                (Boolean) object[3]
        );
    }
}

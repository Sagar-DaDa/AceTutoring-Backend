package com.acetutoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentForExistingStudentDto {
    Long studentId;
    Long availableCourseId;
    Date courseStartDate;
    Date courseEndDate;
}

package com.acetutoring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalCountsDto {
    private Long totalStudents;
    private Long totalTutors;
    private Long totalUsers;
    private Long totalActiveEnrollments;
    private Long totalInactiveEnrollments;
    private Long totalCourses;
    private Long totalAvailableCourses;
}

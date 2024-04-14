package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.CourseDto;
import com.acetutoring.api.dto.AceFileDto;
import com.acetutoring.api.dto.TotalCountsDto;
import com.acetutoring.api.dto.UserDto;
import com.acetutoring.api.entities.AceFile;
import com.acetutoring.api.services.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/admin/api")
public class AdminApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AvailableCourseService availableCourseService;
    @Autowired
    private AuthService authService;
    @Autowired
    private FileService fileService;

    @Value("${project.file}")
    private String path;

    @Value("${project.timetableBaseUrl}")
    private String fileBaseUrl;

    @GetMapping("/adminInfo/{userId}")
    public ResponseEntity<UserDto> getAdminById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/totalCounts")
    public ResponseEntity<TotalCountsDto> getTotalCounts(){
        TotalCountsDto totalCountsDto = new TotalCountsDto(
                studentService.totalStudentCount(),
                tutorService.totalTutorCount(),
                userService.totalUsersCount(),
                enrollmentService.totalActiveEnrollmentsCount(),
                enrollmentService.totalInactiveEnrollmentsCount(),
                courseService.totalCoursesCount(),
                availableCourseService.totalAvailableCoursesCount()
        );

        return ResponseEntity.ok(totalCountsDto);
    }

    @GetMapping("/enrollmentsByCategory")
    public ResponseEntity<List<Object[]>> getAllEnrollmentsLastByGroup(){
        List<Object[]> enrollmentList = enrollmentService.getEnrollmentsByCategory();
        return ResponseEntity.ok(enrollmentList);
    }

    @PutMapping("/changeAdminPassword/{adminId}")
    public String changeAdminPassword(
            @PathVariable Long adminId,
            @RequestBody Map<String, String> newPasswordMap){
        authService.changePassword(adminId, newPasswordMap.get("newPassword"));

        return "Password changed successfully";
    }

    @PostMapping("/uploadTimetable")
    public ResponseEntity<AceFileDto> createCourse(
            @RequestParam("description") String description,
            @RequestParam("timetablePdf") MultipartFile timetable
    ) {

        String timetableName = null;

        AceFileDto aceFileDto = new AceFileDto();
        aceFileDto.setName("timetable");
        aceFileDto.setDescription(description);

        try {
            timetableName = fileService.uploadTimetable(path, timetable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        aceFileDto.setFileUrl(fileBaseUrl + timetableName);

        return ResponseEntity.ok(fileService.createTimetable(aceFileDto));
    }

    @GetMapping("/latestTimetable")
    public ResponseEntity<AceFileDto> getLatestTimetable(){
        return ResponseEntity.ok(fileService.getLatestTimetable());
    }

}

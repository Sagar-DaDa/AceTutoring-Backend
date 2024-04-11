package com.acetutoring.api.dto;

import com.acetutoring.api.entities.Course;
import com.acetutoring.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private Course course;
    private String question;
    private List<String> options;
    private String answer;
    private boolean published;
    private User createdBy;
    private Date createdAt;
    private Date updatedAt;
}

package com.acetutoring.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {
    private Long id;
    private String statement;
    private String customerName;
    private String position;
    private String organization;
    private String imageUrl;
    private Date createdAt;
}

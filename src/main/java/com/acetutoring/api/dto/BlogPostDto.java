package com.acetutoring.api.dto;

import com.acetutoring.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {
    private Long id;
    private String author;
    private String title;
    private String description;
    private String content;
    private String source;
    private String imageUrl;
    private String externalUrl;
    private UserDto createdBy;
    private Date createdAt;
    private Date updatedAt;

    public BlogPostDto(
            Long id,
            String author,
            String title,
            String description,
            String content,
            String source,
            String imageUrl,
            String externalUrl,
            Date createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.content = content;
        this.source = source;
        this.imageUrl = imageUrl;
        this.externalUrl = externalUrl;
        this.createdAt = createdAt;
    }
}

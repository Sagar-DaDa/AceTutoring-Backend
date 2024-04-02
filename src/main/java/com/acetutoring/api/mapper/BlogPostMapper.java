package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.BlogPostDto;
import com.acetutoring.api.entities.BlogPost;

public class BlogPostMapper {
    public static BlogPostDto mapToBlogPostDto(BlogPost blogPost){
        return new BlogPostDto(
                blogPost.getId(),
                blogPost.getAuthor(),
                blogPost.getTitle(),
                blogPost.getDescription(),
                blogPost.getContent(),
                blogPost.getSource(),
                blogPost.getImageUrl(),
                blogPost.getExternalUrl(),
                UserMapper.mapToUserDto(blogPost.getCreatedBy()),
                blogPost.getCreatedAt(),
                blogPost.getUpdatedAt()
        );
    }

    public static BlogPostDto mapToBlogPostDtoPublic(BlogPost blogPost){
        return new BlogPostDto(
                blogPost.getId(),
                blogPost.getAuthor(),
                blogPost.getTitle(),
                blogPost.getDescription(),
                blogPost.getContent(),
                blogPost.getSource(),
                blogPost.getImageUrl(),
                blogPost.getExternalUrl(),
                blogPost.getCreatedAt()
        );
    }

    public static BlogPost mapToBlogPost(BlogPostDto blogPostDto){
        return new BlogPost(
                blogPostDto.getId(),
                blogPostDto.getAuthor(),
                blogPostDto.getTitle(),
                blogPostDto.getDescription(),
                blogPostDto.getContent(),
                blogPostDto.getSource(),
                blogPostDto.getImageUrl(),
                blogPostDto.getExternalUrl(),
                UserMapper.mapToUser(blogPostDto.getCreatedBy()),
                blogPostDto.getCreatedAt(),
                blogPostDto.getUpdatedAt()
        );
    }
}

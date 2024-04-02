package com.acetutoring.api.services;

import com.acetutoring.api.dto.BlogPostDto;

import java.util.List;

public interface BlogPostService {
    BlogPostDto createBlogPost(BlogPostDto blogPostDto);

    BlogPostDto getBlogPostById(Long blogPostId);

    BlogPostDto getBlogPostByIdPublic(Long blogPostId);

    List<BlogPostDto> getAllBlogPost();

    List<BlogPostDto> getAllBlogPostPublic();

    BlogPostDto updateBlogPostDto(Long blogPostId, BlogPostDto blogPostDto);

    void deleteBlogPost(Long blogPostId);
}

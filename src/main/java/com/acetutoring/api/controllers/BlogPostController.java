package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.BlogPostDto;
import com.acetutoring.api.services.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/blogPosts")
public class BlogPostController {
    private BlogPostService blogPostService;

    @PostMapping
    public ResponseEntity<BlogPostDto> createBlogPost(@RequestBody BlogPostDto blogPostDto){
        return ResponseEntity.ok(blogPostService.createBlogPost(blogPostDto));
    }

    @GetMapping("{blogPostId}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable Long blogPostId){
        return ResponseEntity.ok(blogPostService.getBlogPostById(blogPostId));
    }

    @GetMapping
    public ResponseEntity<List<BlogPostDto>> getAllBlogPosts(){
        return ResponseEntity.ok(blogPostService.getAllBlogPost());
    }

    @PutMapping("{blogPostId}")
    public ResponseEntity<BlogPostDto> updateBlogPostByid(
            @PathVariable Long blogPostId,
            @RequestBody BlogPostDto blogPostDto){
        return ResponseEntity.ok(blogPostService.updateBlogPostDto(blogPostId, blogPostDto));
    }

    @DeleteMapping("{blogPostId}")
    public String deleteBlogPostById(@PathVariable Long blogPostId){
        blogPostService.deleteBlogPost(blogPostId);
        return "Blog post deleted successfully.";
    }
}

package com.acetutoring.api.controllers;

import com.acetutoring.api.dto.BlogPostDto;
import com.acetutoring.api.entities.User;
import com.acetutoring.api.mapper.UserMapper;
import com.acetutoring.api.repositories.UserRepo;
import com.acetutoring.api.services.BlogPostService;
import com.acetutoring.api.services.FileService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/blogPosts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepo userRepo;

    @Value("${project.image}")
    private String path;

    @Value("${project.imageBaseUrl}")
    private String imageBaseUrl;

    @PostMapping("/createBlogPost")
    public ResponseEntity<BlogPostDto> createBlogPost(
            @RequestParam("author") String author,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("source") String source,
            @RequestParam("externalUrl") String externalUrl,
            @RequestParam("blogPostImage") MultipartFile blogPostImage
    ) {
        String blogPostImageName = null;

        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setAuthor(author);
        blogPostDto.setTitle(title);
        blogPostDto.setDescription(description);
        blogPostDto.setContent(content);
        blogPostDto.setSource(source);
        blogPostDto.setExternalUrl(externalUrl);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            blogPostImageName = fileService.uploadImage(path, blogPostImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        blogPostDto.setImageUrl(imageBaseUrl + blogPostImageName);

        User user = new User();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        user = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found. Invalid user email."
                ));

        blogPostDto.setCreatedBy(UserMapper.mapToUserDto(user));

        return ResponseEntity.ok(blogPostService.createBlogPost(blogPostDto));
    }

    @GetMapping("{blogPostId}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable Long blogPostId) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(blogPostId));
    }

    @GetMapping
    public ResponseEntity<List<BlogPostDto>> getAllBlogPosts() {
        return ResponseEntity.ok(blogPostService.getAllBlogPost());
    }

    @PutMapping("{blogPostId}")
    public ResponseEntity<BlogPostDto> updateBlogPostByid(
            @PathVariable Long blogPostId,
            @RequestBody BlogPostDto blogPostDto) {
        return ResponseEntity.ok(blogPostService.updateBlogPostDto(blogPostId, blogPostDto));
    }

    @DeleteMapping("/deleteBlogPost/{blogPostId}")
    public String deleteBlogPostById(@PathVariable Long blogPostId) {
        blogPostService.deleteBlogPost(blogPostId);
        return "Blog post deleted successfully.";
    }
}

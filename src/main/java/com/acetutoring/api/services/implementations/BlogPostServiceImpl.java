package com.acetutoring.api.services.implementations;

import com.acetutoring.api.dto.BlogPostDto;
import com.acetutoring.api.entities.BlogPost;
import com.acetutoring.api.exceptions.ResourceNotFoundException;
import com.acetutoring.api.mapper.BlogPostMapper;
import com.acetutoring.api.repositories.BlogPostRepo;
import com.acetutoring.api.services.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private BlogPostRepo blogPostRepo;

    @Override
    public BlogPostDto createBlogPost(BlogPostDto blogPostDto) {

        return BlogPostMapper.mapToBlogPostDto(
                blogPostRepo.save(
                        BlogPostMapper.mapToBlogPost(blogPostDto)
                )
        );
    }

    @Override
    public BlogPostDto getBlogPostById(Long blogPostId) {
        return BlogPostMapper.mapToBlogPostDto(blogPostRepo
                .findById(blogPostId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Blog post not. Invalid blog post Id: " + blogPostId
                        )
                )
        );
    }

    @Override
    public BlogPostDto getBlogPostByIdPublic(Long blogPostId) {
        return BlogPostMapper.mapToBlogPostDtoPublic(blogPostRepo
                .findById(blogPostId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Blog post not. Invalid blog post Id: " + blogPostId
                        )
                )
        );
    }

    @Override
    public List<BlogPostDto> getAllBlogPost() {
        return blogPostRepo
                .findAll()
                .stream()
                .map(BlogPostMapper::mapToBlogPostDto)
                .toList();
    }

    @Override
    public List<BlogPostDto> getAllBlogPostPublic() {
        return blogPostRepo
                .findAll()
                .stream()
                .map(BlogPostMapper::mapToBlogPostDtoPublic)
                .toList();
    }


    @Override
    public BlogPostDto updateBlogPostDto(Long blogPostId, BlogPostDto blogPostDto) {
        BlogPost foundBlogPost = blogPostRepo
                .findById(blogPostId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Blog post not. Invalid blog post Id: " + blogPostId
                        )
                );
        foundBlogPost.setAuthor(blogPostDto.getAuthor());
        foundBlogPost.setTitle(blogPostDto.getTitle());
        foundBlogPost.setDescription(blogPostDto.getDescription());
        foundBlogPost.setContent(blogPostDto.getContent());
        foundBlogPost.setSource(blogPostDto.getSource());
        foundBlogPost.setImageUrl(blogPostDto.getImageUrl());
        foundBlogPost.setExternalUrl(blogPostDto.getExternalUrl());

        return BlogPostMapper.mapToBlogPostDto(blogPostRepo.save(foundBlogPost));
    }

    @Override
    public void deleteBlogPost(Long blogPostId) {
        BlogPost foundBlogPost = blogPostRepo
                .findById(blogPostId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Blog post not. Invalid blog post Id: " + blogPostId
                        )
                );
        blogPostRepo.delete(foundBlogPost);
    }
}

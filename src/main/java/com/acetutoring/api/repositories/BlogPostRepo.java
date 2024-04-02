package com.acetutoring.api.repositories;

import com.acetutoring.api.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
}

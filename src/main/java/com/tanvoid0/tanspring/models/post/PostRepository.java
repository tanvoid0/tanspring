package com.tanvoid0.tanspring.models.post;

import com.tanvoid0.tanspring.models.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

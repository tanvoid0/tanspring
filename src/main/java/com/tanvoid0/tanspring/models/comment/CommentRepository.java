package com.tanvoid0.tanspring.models.comment;

import com.tanvoid0.tanspring.models.comment.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPostId(long postId);
}

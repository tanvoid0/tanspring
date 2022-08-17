package com.tanvoid0.tanspring.models.comment;

import com.tanvoid0.tanspring.models.comment.CommentVO;

import java.util.List;

public interface CommentService {
  CommentVO createComment(long postId, CommentVO commentVO);

  List<CommentVO> getCommentsByPostId(long postId);

  CommentVO getCommentById(Long postId, Long commentId);

  CommentVO updateComment(Long postId, Long commentId, CommentVO commentRequest);

  void deleteComment(Long postId, Long commentId);
}

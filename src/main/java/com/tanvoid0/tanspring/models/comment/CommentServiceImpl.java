package com.tanvoid0.tanspring.models.comment;

import com.tanvoid0.tanspring.models.post.Post;
import com.tanvoid0.tanspring.common.exception.BlogAPIException;
import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.post.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private PostRepository postRepository;

  private ModelMapper mapper;

  public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.mapper = mapper;
  }

  @Override
  public CommentVO createComment(long postId, CommentVO commentVO) {
    Comment comment = mapToEntity(commentVO);

    // retrieve post entity by id
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    // set post to comment entity
    comment.setPost(post);

    // save comment entity to DB
    Comment newComment = commentRepository.save(comment);
    return mapToDTO(newComment);
  }

  @Override
  public List<CommentVO> getCommentsByPostId(long postId) {
    // retrieve comments by postId
    final List<Comment> comments = commentRepository.findByPostId(postId);

    return comments.stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  @Override
  public CommentVO getCommentById(Long postId, Long commentId) {
    // retrieve post entity by id
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    // retrieve comment entity by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

    if (!comment.getPost().getId().equals(post.getId())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }
    return mapToDTO(comment);
  }

  @Override
  public CommentVO updateComment(Long postId, Long commentId, CommentVO commentRequest) {
    // retrieve post entity by id
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    // retrieve comment entity by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

    if (!comment.getPost().getId().equals(post.getId())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }

    comment.setName(commentRequest.getName());
    comment.setEmail(commentRequest.getEmail());
    comment.setBody(commentRequest.getBody());

    Comment updatedComment = commentRepository.save(comment);
    return mapToDTO(updatedComment);
  }

  @Override
  public void deleteComment(Long postId, Long commentId) {
    // retrieve post entity by id
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

    // retrieve comment entity by id
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

    if (!comment.getPost().getId().equals(post.getId())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
    }

    commentRepository.delete(comment);
  }

  private CommentVO mapToDTO(Comment comment) {
    final CommentVO commentVO = mapper.map(comment, CommentVO.class);
    return commentVO;
  }

  private Comment mapToEntity(CommentVO commentVO) {
    Comment comment = mapper.map(commentVO, Comment.class);
    return comment;
  }
}

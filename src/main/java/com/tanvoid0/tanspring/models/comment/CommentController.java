package com.tanvoid0.tanspring.models.comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@Api(value = "CRUD REST APIs for Comment Resource")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @ApiOperation(value = "Create Comment REST API")
  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<CommentVO> createComment(@PathVariable(value = "postId") long postId,
                                                 @Valid @RequestBody CommentVO commentVO) {
    return new ResponseEntity<>(commentService.createComment(postId, commentVO), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Get ALl Comments by Post ID REST API")
  @GetMapping("/posts/{postId}/comments")
  public List<CommentVO> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
    return commentService.getCommentsByPostId(postId);
  }

  @ApiOperation(value = "GET single comment by Id REST API")
  @GetMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentVO> getCommentById(@PathVariable(value = "postId") Long postId,
                                                  @PathVariable(value = "id") Long commentId) {
    CommentVO commentVO = commentService.getCommentById(postId, commentId);
    return new ResponseEntity<>(commentVO, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Comment By ID REST API")
  @PutMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentVO> updateComment(@PathVariable(value = "postId") Long postId,
                                                 @PathVariable(value = "id") Long commentId,
                                                 @Valid @RequestBody CommentVO commentVO) {
    CommentVO updatedComment = commentService.updateComment(postId, commentId, commentVO);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }

  @ApiOperation(value = "Delete Comment By Id REST API")
  @DeleteMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "id") Long commentId) {
    commentService.deleteComment(postId, commentId);
    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }
}

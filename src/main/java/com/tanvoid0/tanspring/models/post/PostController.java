package com.tanvoid0.tanspring.models.post;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.tanvoid0.tanspring.utils.AppConstants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "CRUD REST APIs for Post Resources")
@RestController
@RequestMapping()
public class PostController {

  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @ApiOperation(value = "Create Post REST API")
  @PreAuthorize("hasRole('ADMIN')")
  // create blog post rest api
  @PostMapping("/api/v1/posts")
  public ResponseEntity<PostVO> createPost(@Valid @RequestBody PostVO postVO) {
    return new ResponseEntity<>(postService.createPost(postVO), HttpStatus.CREATED);
  }

  @ApiOperation(value = "GET all Post REST API")
  // get all posts rest api
  @GetMapping("/api/v1/posts")
  public PostResponse getAllPosts(
      @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
  ) {
    return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
  }

  @ApiOperation(value = "GET Post by Id REST API")
  // get post by id
  @GetMapping(value = "/api/v1/posts/{id}")
  public ResponseEntity<PostVO> getPostByIdV1(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }

  @ApiOperation(value = "Update Post by Id REST API")
  @PreAuthorize("hasRole('ADMIN')")
  // update post by id rest api
  @PutMapping("/api/v1/posts/{id}")
  public ResponseEntity<PostVO> updatePost(@Valid @RequestBody PostVO postVO, @PathVariable(name = "id") long id) {

    PostVO postResponse = postService.updatePost(postVO, id);

    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  @ApiOperation(value = "Delete Post by Id REST API")
  @PreAuthorize("hasRole('ADMIN')")
  // delete post rest api
  @DeleteMapping("/api/v1/posts/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {

    postService.deletePostById(id);

    return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
  }
}

package com.tanvoid0.tanspring.models.post;

import com.tanvoid0.tanspring.models.comment.CommentVO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Post model information")
@Data
public class PostVO {

  @ApiModelProperty(value = "Blog post id")
  private long id;

  // title cannot be null or empty
  // title cannot be less than 2 characters
  @ApiModelProperty(value = "Blog post title")
  @NotEmpty
  @Size(min = 2, message = "Post title should have at least 2 characters")
  private String title;

  // post description could not be null or empty
  // post description should have at least 10 characters
//  @ApiModelProperty(value = "Post post description")
  @NotEmpty
  @Size(min = 10, message = "Post description should have at least 10 characters")
  private String description;

  // post content should not be null or empty
  @ApiModelProperty(value = "Blog post content")
  @NotEmpty
  private String content;

  @ApiModelProperty(value = "Blog post comments")
  private Set<CommentVO> comments = new HashSet<>();
}

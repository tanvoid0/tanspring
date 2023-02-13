package com.tanvoid0.tanspring.models.comment;

import lombok.Data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentVO {

  @ApiModelProperty(value = "Comment id")
  private long id;

  // name should not be null or empty
  @ApiModelProperty(value = "Comment name")
  @NotEmpty(message = "Name should not be null or empty")
  private String name;

  // email should not be null or empty
  // email field validation
  @ApiModelProperty(value = "Comment email")
  @NotEmpty(message = "Email should not be null or empty")
  @Email

  private String email;

  // comment body should not be null or empty
  // comment body must be minimum 10 characters
  @ApiModelProperty(value = "Comment body")
  @NotEmpty
  @Size(min = 10, message = "Comment body should not be null or empty")
  private String body;
}

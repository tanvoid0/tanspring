package com.tanvoid0.tanspring.models.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoVO {
  private long id;
  @NotNull
  private String title;
  private String description;
  private String icon;
  private ZonedDateTime deadline;
}

package com.tanvoid0.tanspring.models.todo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class NewTodoVO {
  @Setter(AccessLevel.NONE)
  private static final String _type = "NewTodo";
  private String id;
  @NotNull
  private String title;
  private String description;
  private String icon;
  private ZonedDateTime deadline;
  private ZonedDateTime reminder;
}

package com.tanvoid0.tanspring.models.todo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class TodoVO {
  @Setter(AccessLevel.NONE)
  private static final String _type = "Todo";

  private long id;
  @NonNull
  private String title;
  private String description;
  private String icon;
  private ZonedDateTime deadline;
  private ZonedDateTime reminder;

  private Date createdAt;
  private Date updatedAt;

  @NonNull
  private long userId;
}

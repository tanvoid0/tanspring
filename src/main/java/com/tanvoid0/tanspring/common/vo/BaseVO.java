package com.tanvoid0.tanspring.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseVO {
  protected long id;
  protected long version;
  protected LocalDateTime createAt;
  protected LocalDateTime updatedAt;
}

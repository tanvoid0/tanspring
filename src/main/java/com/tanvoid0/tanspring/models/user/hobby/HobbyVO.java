package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HobbyVO extends BaseVO {
  private long id;

  @NotNull
  private String title;

  private String icon;

  private String image;

  private Long orderSeq;
}

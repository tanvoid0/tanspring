package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SocialVO extends BaseVO {
  @NotNull
  private String title;

  @NotNull
  private String url;

  private String image;

  private String icon;

  private Long orderSeq;
}

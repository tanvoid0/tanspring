package com.tanvoid0.tanspring.models.user.portfolio.oj;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OnlineJudgeVO extends BaseVO {
  private String name;
  private String icon;
  private String progress;
  private String url;
  private Long orderSec;
}

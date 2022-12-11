package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.ProjectVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PortfolioVO {
  private long id;
  private long version;
  private List<OnlineJudgeVO> onlineJudges = new ArrayList<>();
  private List<ProjectVO> projects = new ArrayList<>();
}

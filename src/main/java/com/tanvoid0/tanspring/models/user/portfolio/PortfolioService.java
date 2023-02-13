package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.models.user.UpdateUserInfoVO;
import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.UserVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.NewOnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.NewProjectVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.ProjectVO;

import java.util.List;

public interface PortfolioService extends CustomService<Portfolio, PortfolioVO, NewPortfolioVO, UpdatePortfolio> {
  Portfolio findOrCreateByUser();

  Portfolio findOrCreateByUser(AppUser user);

  PortfolioVO addOnlineJudge(List<NewOnlineJudgeVO> newVOs);

  OnlineJudgeVO addOnlineJudge(NewOnlineJudgeVO newItem);

  List<ProjectVO> addProject(List<NewProjectVO> newVOs);

  ProjectVO addProject(NewProjectVO newVO);

  UserVO updateInfo(UpdateUserInfoVO updateVO) throws Exception;

  UserVO getUserPortfolio(String username);

  List<ProjectVO> getProject();

  ProjectVO getProjectById(long id);

  boolean deleteProject(long id);

  List<OnlineJudgeVO> getOnlineJudge();

}

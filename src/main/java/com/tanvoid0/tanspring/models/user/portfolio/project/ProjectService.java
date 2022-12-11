package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.template.CustomService;

import java.util.List;

public interface ProjectService extends CustomService<Project, ProjectVO, NewProjectVO, UpdateProjectVO> {
  List<ProjectVO> getByPortfolioId(long portfolioId);
}

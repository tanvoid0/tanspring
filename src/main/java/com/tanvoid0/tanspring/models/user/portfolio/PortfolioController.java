package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.models.user.UpdateUserInfoVO;
import com.tanvoid0.tanspring.models.user.UserVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.NewOnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.NewProjectVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.ProjectVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/portfolio")
@Slf4j
public class PortfolioController {

  @Autowired
  PortfolioService service;

  @PostMapping("/oj/batch")
  @PreAuthorize("hasRole('USER')")
  public PortfolioVO addOnlineJudge(
      @Valid @RequestBody final List<NewOnlineJudgeVO> newVOs
  ) {
    return service.addOnlineJudge(newVOs);
  }

  @PostMapping("/oj")
  @PreAuthorize("hasRole('USER')")
  public OnlineJudgeVO addOnlineJudge(
      @Valid @RequestBody final NewOnlineJudgeVO newVO
  ) {
    return service.addOnlineJudge(newVO);
  }

  @PostMapping("/project/batch")
  @PreAuthorize("hasRole('USER')")
  public List<ProjectVO> addProject(
      @Valid @RequestBody final List<NewProjectVO> newVOs
  ) {
    return service.addProject(newVOs);
  }

  @PostMapping("/project")
  @PreAuthorize("hasRole('USER')")
  public ProjectVO addProject(
      @Valid @RequestBody final NewProjectVO newVO
  ) {
    return service.addProject(newVO);
  }

  @PutMapping("/info")
  @ResponseStatus(HttpStatus.OK)
//  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MODERATOR')")
  public UserVO updateInfo(
      @Valid @RequestBody final UpdateUserInfoVO updateVO
  ) throws Exception {
    log.info("Update info: {}", updateVO);
    return service.updateInfo(updateVO);
  }

  @DeleteMapping("/project/{id}")
  @PreAuthorize("hasRole('USER')")
  public boolean deleteProject(@PathVariable("id") final long id) {
    return service.deleteProject(id);
  }

  @GetMapping("/user/{username}")
  public UserVO getUserPortfolio(
      @PathVariable("username") final String username
  ) {
    return service.getUserPortfolio(username);
  }

  @GetMapping("/project")
  @PreAuthorize("hasRole('USER')")
  public List<ProjectVO> getProject() {
    return service.getProject();
  }

  @GetMapping("/project/{id}")
  @PreAuthorize("hasRole('USER')")
  public ProjectVO getProject(
      @PathVariable("id") final long id
  ) {
    return service.getProjectById(id);
  }

  @GetMapping("/oj")
  @PreAuthorize("hasRole('USER')")
  public List<OnlineJudgeVO> getOnlineJudge() {
    return service.getOnlineJudge();
  }
}

package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.UserVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.NewOnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudge;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudgeRepository;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudgeVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.NewProjectVO;
import com.tanvoid0.tanspring.models.user.portfolio.project.ProjectServiceImpl;
import com.tanvoid0.tanspring.models.user.portfolio.project.ProjectVO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("portfolioService")
public class PortfolioServiceImpl implements PortfolioService {
  @Autowired
  private PortfolioRepository repository;

  @Autowired
  private OnlineJudgeRepository onlineJudgeRepository;

  @Autowired
  private ProjectServiceImpl projectService;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper mapper;

  @Override
  public Portfolio findOrCreateByUser() {
    final User user = userService.getAuthUser();
    return this.findOrCreateByUser(user);
  }

  @Override
  public Portfolio findOrCreateByUser(User user) {
    return repository.findByUser(user)
        .orElseGet(() -> repository.save(Portfolio.builder().user(user).build()));
  }

  @Override
  public List<PortfolioVO> get() {
    return null;
  }

  @Override
  public PortfolioVO get(long id) {
    final Portfolio entity = this.findOrCreateByUser();
    return convertEntityToVO(entity);
  }

  @Transactional
  @Override
  public PortfolioVO addOnlineJudge(final List<NewOnlineJudgeVO> newVOs) {
    final PortfolioVO portfolio = convertEntityToVO(this.findOrCreateByUser());
    List<OnlineJudgeVO> ojs = newVOs.stream().map(this::addOnlineJudge).collect(Collectors.toList());
    portfolio.setOnlineJudges(ojs);
    return portfolio;
  }

  @Transactional
  @Override
  public OnlineJudgeVO addOnlineJudge(NewOnlineJudgeVO newItem) {
    final Portfolio portfolio = this.findOrCreateByUser();
    final OnlineJudge entity = mapper.map(newItem, OnlineJudge.class);
    entity.setPortfolio(portfolio);
    final OnlineJudge savedEntity = onlineJudgeRepository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Transactional
  @Override
  public List<ProjectVO> addProject(final List<NewProjectVO> newVOs) {
    final PortfolioVO portfolioVO = convertEntityToVO(this.findOrCreateByUser());
    final List<ProjectVO> projectVOs = projectService.add(newVOs);
    portfolioVO.setProjects(projectVOs);
    return projectVOs;
  }

  @Override
  public ProjectVO addProject(NewProjectVO newVO) {
    return projectService.add(newVO);
  }

  @Override
  public UserVO getUserPortfolio(String username) {
    return userService.get(username);
  }

  @Override
  public List<ProjectVO> getProject() {
    final Portfolio portfolio = this.findOrCreateByUser();
    return projectService.getByPortfolioId(portfolio.getId());
  }

  @Override
  public boolean deleteProject(long id) {
    return projectService.delete(id);
  }

  @Override
  public PortfolioVO add(NewPortfolioVO newPortfolioVO) {
    return null;
  }

  @Override
  public List<PortfolioVO> add(List<NewPortfolioVO> newPortfolioVOS) {
    return null;
  }

  @Override
  public PortfolioVO update(UpdatePortfolio updatePortfolio) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    return false;
  }

  @Override
  public Portfolio findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Portfolio", "id", id));
  }

  @Override
  public PortfolioVO convertEntityToVO(Portfolio portfolio) {
    return mapper.map(portfolio, PortfolioVO.class);
  }

  private OnlineJudgeVO convertEntityToVO(OnlineJudge entity) {
    return mapper.map(entity, OnlineJudgeVO.class);
  }

  @Override
  public Portfolio convertVOToEntity(PortfolioVO portfolioVO) {
    return mapper.map(portfolioVO, Portfolio.class);
  }
}

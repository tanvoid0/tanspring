package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;
import com.tanvoid0.tanspring.models.user.portfolio.PortfolioService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Transactional
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectRepository repository;

  @Autowired
  @Lazy
  private PortfolioService portfolioService;

  @Autowired
  private ModelMapper mapper;

  @Override
  public List<ProjectVO> get() {
    return repository.findAll().stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public ProjectVO get(long id) {
    final Project project = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    return convertEntityToVO(project);
  }

  @Transactional
  @Override
  public ProjectVO add(NewProjectVO newProjectVO) {
    final Portfolio portfolio = portfolioService.findOrCreateByUser();
    final Project entity = mapper.map(newProjectVO, Project.class);
    entity.setPortfolio(portfolio);
    final Project savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<ProjectVO> add(List<NewProjectVO> newProjectVOS) {
    return newProjectVOS.stream().map(this::add).toList();
  }

  @Override
  public ProjectVO update(UpdateProjectVO updateProjectVO) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    final Project entity = findEntity(id);
    repository.delete(entity);
    return true;
  }

  @Override
  public Project findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
  }

  @Override
  public ProjectVO convertEntityToVO(Project project) {
//    final Set<ImageLink> images = project.getImages();
//    final Set<Tag> tags = project.getTags();

    final ProjectVO projectVO = mapper.map(project, ProjectVO.class);
    return projectVO;
//    projectVO.setTags(tags);
  }

  @Override
  public Project convertVOToEntity(ProjectVO projectVO) {
    return mapper.map(projectVO, Project.class);
  }

  @Override
  public List<ProjectVO> getByPortfolioId(long portfolioId) {
    final List<Project> projects = repository.findByPortfolio_Id(portfolioId);
    final List<ProjectVO> projectVOs = projects.stream().map(this::convertEntityToVO).toList();
    return projectVOs;
  }
}

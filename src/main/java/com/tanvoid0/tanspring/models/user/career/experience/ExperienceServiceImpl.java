package com.tanvoid0.tanspring.models.user.career.experience;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import jakarta.validation.Valid;

@Service("experienceService")
public class ExperienceServiceImpl implements ExperienceService {

  private final ExperienceRepository repository;

  private final CareerService careerService;

  private final ModelMapper mapper;

  public ExperienceServiceImpl(ExperienceRepository repository, CareerService careerService, ModelMapper mapper) {
    this.repository = repository;
    this.careerService = careerService;
    this.mapper = mapper;
  }

  @Override
  public List<ExperienceVO> get() {
    final Career career = careerService.findOrCreateByUser();
    final List<Experience> list = repository.findByCareer(career);
    return list.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public ExperienceVO get(final long id) {
    return convertEntityToVO(findEntity(id));
  }

  @PostMapping
  @Override
  public ExperienceVO add(@Valid @RequestBody final NewExperienceVO newExperienceVO) {
    final Career career = careerService.findOrCreateByUser();
    Experience entity = mapper.map(newExperienceVO, Experience.class);
    entity.setCareer(career);
    final Experience savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<ExperienceVO> add(List<NewExperienceVO> newExperienceVOS) {
    return newExperienceVOS.stream().map(this::add).toList();
  }

  @PutMapping("/{id}")
  @Override
  public ExperienceVO update(UpdateExperienceVO updateExperienceVO) {
    final Experience entity = findEntity(updateExperienceVO.getId());
    mapper.map(updateExperienceVO, entity);
    final Experience savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public boolean delete(long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Experience findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Experience", "id", id));
  }

  @Override
  public ExperienceVO convertEntityToVO(Experience experience) {
    return mapper.map(experience, ExperienceVO.class);
  }

  @Override
  public Experience convertVOToEntity(ExperienceVO experienceVO) {
    return mapper.map(experienceVO, Experience.class);
  }
}

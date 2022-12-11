package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("achievementService")
public class AchievementServiceImpl implements AchievementService {
  private final AchievementRepository repository;
  private final CareerService careerService;

  private final ModelMapper mapper;

  public AchievementServiceImpl(AchievementRepository repository, CareerService careerService, ModelMapper mapper) {
    this.repository = repository;
    this.careerService = careerService;
    this.mapper = mapper;
  }

  @Override
  public List<AchievementVO> get() {
    final Career career = careerService.findOrCreateByUser();
    final List<Achievement> list = repository.findByCareer(career);
    return list.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public AchievementVO get(final long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public AchievementVO add(final NewAchievementVO newAchievementVO) {
    final Career career = careerService.findOrCreateByUser();
    Achievement entity = mapper.map(newAchievementVO, Achievement.class);
    entity.setCareer(career);
    final Achievement savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<AchievementVO> add(final List<NewAchievementVO> newAchievementVOS) {
    return newAchievementVOS.stream().map(this::add).toList();
  }

  @Override
  public AchievementVO update(final UpdateAchievementVO updateAchievementVO) {
    final Achievement entity = findEntity(updateAchievementVO.getId());
    mapper.map(updateAchievementVO, entity);
    final Achievement savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public boolean delete(long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Achievement findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Achievement", "id", id));
  }

  @Override
  public AchievementVO convertEntityToVO(Achievement achievement) {
    return mapper.map(achievement, AchievementVO.class);
  }

  @Override
  public Achievement convertVOToEntity(AchievementVO achievementVO) {
    return mapper.map(achievementVO, Achievement.class);
  }
}

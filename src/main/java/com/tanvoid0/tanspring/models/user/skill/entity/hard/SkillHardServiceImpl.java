package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.skill.SkillServiceImpl;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("skillHardService")
public class SkillHardServiceImpl implements SkillHardService {

  final SkillHardRepository repository;
  final SkillItemRepository skillItemRepository;

  final SkillServiceImpl skillService;

  final ModelMapper mapper;

  public SkillHardServiceImpl(
      final SkillHardRepository repository,
      final SkillItemRepository skillItemRepository,
      @Lazy final SkillServiceImpl skillService,
      final ModelMapper mapper) {
    this.repository = repository;
    this.skillItemRepository = skillItemRepository;
    this.skillService = skillService;
    this.mapper = mapper;
  }

  // GET
  @Override
  public SkillHardVO get(long id) {
    final SkillHard entity = findById(id);
    return convertEntityToVO(entity);
  }

  @Override
  public Set<SkillItemVO> getItems(long id) {
    return skillItemRepository.findBySkillId(id).stream().map(this::convertEntityToVO).collect(Collectors.toSet());
  }


  // POST
  @Override
  public List<SkillHardVO> addSkillHard(final List<NewSkillItemVO> newVOs) {
    return newVOs.stream().map(this::addSkillHard).toList();
  }

  @Override
  public SkillHardVO addSkillHard(final NewSkillItemVO newItemVO) {
    final SkillEntity skillEntity = skillService.findOrCreateByUser();
    final SkillHard entity = mapper.map(newItemVO, SkillHard.class);
    entity.setSkill(skillEntity);
    final SkillHard savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public SkillHardVO addSkillItem(final List<NewSkillItemVO> newItemVO, final long hardSkillId) {
    final SkillHardVO parentSkill = this.get(hardSkillId);
    final Set<SkillItemVO> childSkills = newItemVO.stream().map((final NewSkillItemVO item) -> this.addSkillItem(item, hardSkillId)).collect(Collectors.toSet());
    parentSkill.setItems(childSkills);
    return parentSkill;
  }

  @Override
  public SkillItemVO addSkillItem(final NewSkillItemVO newVO, final long parentSkillId) {
    final SkillHard parentSkill = findById(parentSkillId);
    final SkillItem entity = mapper.map(newVO, SkillItem.class);
    entity.setSkill(parentSkill);

    final SkillItem savedEntity = skillItemRepository.save(entity);

    return convertEntityToVO(savedEntity);
  }

  // Private methods
  private SkillHard findById(long id) {
    return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SkillHard", "id", id));
  }

  private SkillHardVO convertEntityToVO(final SkillHard entity) {
    return mapper.map(entity, SkillHardVO.class);
  }

  private SkillItemVO convertEntityToVO(final SkillItem entity) {
    return mapper.map(entity, SkillItemVO.class);
  }
}

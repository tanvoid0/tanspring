package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.skill.SkillService;
import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.skill.entity.UpdateSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguage;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguageRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("skillFrameworkService")
public class SkillFrameworkServiceImpl implements SkillFrameworkService {
  @Autowired
  private SkillFrameworkRepository repository;

  @Autowired
  private SkillLanguageRepository skillLanguageRepository;

  @Autowired
  @Lazy
  private SkillService skillService;

  @Autowired
  private ModelMapper mapper;

  @Override
  public List<SkillFrameworkVO> get() {
    final SkillEntity skillEntity = skillService.findOrCreateByUser();
    final Set<SkillFramework> frameworks = repository.findBySkillId(skillEntity.getId());
    return frameworks.stream().map(this::convertEntityToVO).collect(Collectors.toList());
  }

  @Override
  public SkillFrameworkVO get(long id) {
    return null;
  }

  @Override
  public SkillFrameworkVO add(NewSkillFrameworkVO newSkillVO) {
    final SkillEntity skillEntity = skillService.findOrCreateByUser();
    final SkillFramework entity = mapper.map(newSkillVO, SkillFramework.class);
    entity.setSkill(skillEntity);
    final SkillFramework savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<SkillFrameworkVO> add(final List<NewSkillFrameworkVO> newSkillVOS) {
    return newSkillVOS.stream().map(this::add).toList();
  }

  @Override
  public SkillFrameworkVO update(final UpdateSkillVO updateSkillVO) {
    return null;
  }

  @Override
  public boolean delete(final long id) {
    return false;
  }

  @Override
  public SkillFramework findEntity(final long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SkillFramework", "id", id));
  }

  public SkillLanguage findLanguageEntity(final long id) {
    return skillLanguageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SkillLanguage", "id", id));
  }

  @Override
  public SkillFrameworkVO convertEntityToVO(final SkillFramework skillFramework) {
    return mapper.map(skillFramework, SkillFrameworkVO.class);
  }

  private BaseSkillVO convertEntityToVO(final SkillLanguage entity) {
    return mapper.map(entity, BaseSkillVO.class);
  }

  @Override
  public SkillFramework convertVOToEntity(final SkillFrameworkVO skillFrameworkVO) {
    return mapper.map(skillFrameworkVO, SkillFramework.class);
  }

  @Override
  public List<BaseSkillVO> addLanguage(List<NewSkillItemVO> newSkillLanguageVOs) {
    return newSkillLanguageVOs.stream().map(this::addLanguage).toList();
  }

  @Override
  public BaseSkillVO addLanguage(NewSkillItemVO newVO) {
    final SkillLanguage entity = mapper.map(newVO, SkillLanguage.class);
    final SkillLanguage savedEntity = skillLanguageRepository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public SkillFrameworkVO linkLanguage(long id, long languageId) {
    final SkillFramework framework = findEntity(id);
    final SkillLanguage language = findLanguageEntity(languageId);
    framework.getLanguages().add(language);
    final SkillFramework savedEntity = repository.save(framework);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public SkillFrameworkVO linkLanguageRemove(long id, long languageId) {
    final SkillFramework framework = findEntity(id);
    final SkillLanguage language = findLanguageEntity(languageId);
    framework.getLanguages().remove(language);
    final SkillFramework savedEntity = repository.save(framework);
    return convertEntityToVO(savedEntity);
  }
}

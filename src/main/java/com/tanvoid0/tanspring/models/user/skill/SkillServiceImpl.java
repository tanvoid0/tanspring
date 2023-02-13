package com.tanvoid0.tanspring.models.user.skill;

import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntityVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHard;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardRepository;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardService;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardVO;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguistic;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguisticRepository;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoft;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoftRepository;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoftVO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("skillService")
public class SkillServiceImpl implements SkillService {
  final UserService userService;
  final SkillRepository repository;
  final SkillSoftRepository skillSoftRepository;
  final SkillHardRepository skillHardRepository;
  final SkillLinguisticRepository skillLinguisticRepository;
  final SkillHardService skillHardService;
  final ModelMapper mapper;

  public SkillServiceImpl(final UserService userService, final SkillRepository repository, final SkillSoftRepository skillSoftRepository, final SkillHardRepository skillHardRepository, SkillLinguisticRepository skillLinguisticRepository,
                          final SkillHardService skillHardService,
                          final ModelMapper mapper) {
    this.userService = userService;
    this.repository = repository;
    this.skillSoftRepository = skillSoftRepository;
    this.skillHardRepository = skillHardRepository;
    this.skillLinguisticRepository = skillLinguisticRepository;
    this.skillHardService = skillHardService;
    this.mapper = mapper;
  }

  @Override
  public SkillEntityVO get() {
    final SkillEntity entity = this.findOrCreateByUser();
    final SkillEntityVO entityVO = convertEntityToVO(entity);
    entityVO.getHardSkills().forEach((SkillHardVO item) -> item.setItems(skillHardService.getItems(item.getId())));
    return entityVO;
  }

  @Override
  public SkillEntity findOrCreateByUser() {
    final AppUser user = userService.getAuthUser();
    return this.findOrCreateByUser(user);
  }

  @Override
  public SkillEntity findOrCreateByUser(AppUser user) {
    return repository.findByUser(user).orElseGet(() -> repository.save(SkillEntity.builder().user(user).build()));
  }

  @Override
  public List<SkillSoftVO> addSkillSoft(List<NewSkillItemVO> items) {
    return items.stream().map(this::addSkillSoft).toList();
  }

  @Override
  public SkillSoftVO addSkillSoft(final NewSkillItemVO newItemVO) {
    final SkillEntity skillEntity = this.findOrCreateByUser();
    final SkillSoft entity = mapper.map(newItemVO, SkillSoft.class);
    entity.setSkill(skillEntity);
    final SkillSoft savedEntity = skillSoftRepository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<SkillLinguisticVO> addSkillLinguistic(List<NewSkillItemVO> items) {
    return items.stream().map(this::addSkillLinguistic).toList();
  }

  @Override
  public SkillLinguisticVO addSkillLinguistic(final NewSkillItemVO newItemVO) {
    final SkillEntity skillEntity = this.findOrCreateByUser();
    final SkillLinguistic entity = mapper.map(newItemVO, SkillLinguistic.class);
    entity.setSkill(skillEntity);
    final SkillLinguistic savedEntity = skillLinguisticRepository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  private SkillLinguisticVO convertEntityToVO(SkillLinguistic entity) {
    return mapper.map(entity, SkillLinguisticVO.class);
  }

  private SkillSoftVO convertEntityToVO(final SkillSoft entity) {
    return mapper.map(entity, SkillSoftVO.class);
  }

  public SkillEntityVO convertEntityToVO(final SkillEntity entity) {
    return mapper.map(entity, SkillEntityVO.class);
  }

  public SkillHardVO convertEntityToVO(final SkillHard entity) {
    return mapper.map(entity, SkillHardVO.class);
  }

  public SkillSoft convertVOToEntity(final SkillSoftVO vo) {
    return mapper.map(vo, SkillSoft.class);
  }

  public SkillEntity convertVOToEntity(final SkillEntityVO vo) {
    return mapper.map(vo, SkillEntity.class);
  }

}

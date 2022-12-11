package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import java.util.List;
import java.util.Set;

public interface SkillHardService {
  SkillHardVO get(long id);

  Set<SkillItemVO> getItems(long id);

  // POST
  List<SkillHardVO> addSkillHard(List<NewSkillItemVO> newVOs);

  // POST
  SkillHardVO addSkillHard(NewSkillItemVO newItemVO);

  SkillHardVO addSkillItem(List<NewSkillItemVO> newItemVO, long hardSkillId);

  SkillItemVO addSkillItem(NewSkillItemVO newVO, long parentSkillId);
}

package com.tanvoid0.tanspring.models.user.skill;

import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntityVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoftVO;

import java.util.List;

public interface SkillService {
  SkillEntityVO get();

  SkillEntity findOrCreateByUser();

  SkillEntity findOrCreateByUser(User user);

  List<SkillSoftVO> addSkillSoft(List<NewSkillItemVO> items);

  SkillSoftVO addSkillSoft(NewSkillItemVO item);

  List<SkillLinguisticVO> addSkillLinguistic(List<NewSkillItemVO> items);

  SkillLinguisticVO addSkillLinguistic(NewSkillItemVO newItemVO);
}

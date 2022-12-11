package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.common.template.CustomController;
import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.UpdateSkillVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/skill/framework")
public class SkillFrameworkController implements CustomController<SkillFrameworkVO, NewSkillFrameworkVO, UpdateSkillVO> {
  @Autowired
  private SkillFrameworkService service;

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  @Override
  public List<SkillFrameworkVO> get() {
    return service.get();
  }

  @Override
  public SkillFrameworkVO get(long id) {
    return null;
  }


  @PostMapping
  @PreAuthorize("hasRole('USER')")
  @Override
  public SkillFrameworkVO add(
      @Valid @RequestBody final NewSkillFrameworkVO newSkillFrameworkVO) {
    return service.add(newSkillFrameworkVO);
  }

  @PostMapping("/batch")
  @PreAuthorize("hasRole('USER')")
  @Override
  public List<SkillFrameworkVO> add(
      @Valid @RequestBody List<NewSkillFrameworkVO> newSkillFrameworkVOS) {
    return service.add(newSkillFrameworkVOS);
  }

  @PostMapping("/language/batch")
  @PreAuthorize("hasRole('USER')")
  public List<BaseSkillVO> addLanguage(
      @Valid @RequestBody List<NewSkillItemVO> newSkillLanguageVOs
  ) {
    return service.addLanguage(newSkillLanguageVOs);
  }

  @PostMapping("/language")
  @PreAuthorize("hasRole('USER')")
  public BaseSkillVO addLanguage(
      @Valid @RequestBody NewSkillItemVO newItem
  ) {
    return service.addLanguage(newItem);
  }

  @PostMapping("/{id}/language/{languageId}/link")
  @PreAuthorize("hasRole('USER')")
  public SkillFrameworkVO addLanguage(
      @PathVariable("id") final long id,
      @PathVariable("languageId") final long languageId
  ) {
    return service.linkLanguage(id, languageId);
  }

  @DeleteMapping("/{id}/language/{languageId}/link")
  @PreAuthorize("hasRole('USER')")
  public SkillFrameworkVO removeLanguage(
      @PathVariable("id") final long id,
      @PathVariable("languageId") final long languageId
  ) {
    return service.linkLanguageRemove(id, languageId);
  }

  @Override
  public SkillFrameworkVO update(long id, UpdateSkillVO updateSkillVO) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    return false;
  }
}

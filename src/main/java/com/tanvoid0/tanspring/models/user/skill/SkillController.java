package com.tanvoid0.tanspring.models.user.skill;

import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntityVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.NewSkillItemVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardService;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHardVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillItemVO;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoftVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/skill")
@Slf4j
public class SkillController {
  private final SkillService service;

  private final SkillHardService hardSkillService;

  public SkillController(
      final SkillService service,
      final SkillHardService hardSkillService) {
    this.service = service;
    this.hardSkillService = hardSkillService;
  }

  @GetMapping
  public SkillEntityVO get() {
    return service.get();
  }

  @PostMapping("/soft/batch")
  @PreAuthorize("hasRole('USER')")
  public List<SkillSoftVO> addSkillSoft(@Valid @RequestBody List<NewSkillItemVO> items) {
    return service.addSkillSoft(items);
  }

  @PostMapping("/linguistic/batch")
  @PreAuthorize("hasRole('USER')")
  public List<SkillLinguisticVO> addSkillLinguistic(@Valid @RequestBody List<NewSkillItemVO> items) {
    log.debug("Adding {} linguistic units.", items.size());
    return service.addSkillLinguistic(items);
  }

  @PostMapping("/hard/batch")
  @PreAuthorize("hasRole('USER')")
  public List<SkillHardVO> addSkillHard(@Valid @RequestBody List<NewSkillItemVO> items) {
    return hardSkillService.addSkillHard(items);
  }

  @PostMapping("/hard/{id}")
  @PreAuthorize("hasRole('USER')")
  public SkillItemVO addSkillHardItem(
      @PathVariable("id") final long id,
      @Valid @RequestBody NewSkillItemVO newItem
  ) {
    return hardSkillService.addSkillItem(newItem, id);
  }

  @PostMapping("/hard/{id}/batch")
  @PreAuthorize("hasRole('USER')")
  public SkillHardVO addSkillHardItem(
      @Valid @RequestBody List<NewSkillItemVO> items,
      @PathVariable("id") final long id
  ) {
    return hardSkillService.addSkillItem(items, id);
  }
}

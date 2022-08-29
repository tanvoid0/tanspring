package com.tanvoid0.tanspring.models.user.career.experience;

import com.tanvoid0.tanspring.common.template.CustomController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/experience")
public class ExperienceController implements CustomController<ExperienceVO, NewExperienceVO, UpdateExperienceVO> {
  private final ExperienceService service;

  public ExperienceController(ExperienceService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<ExperienceVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public ExperienceVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @Override
  public ExperienceVO add(@Valid @RequestBody final NewExperienceVO newExperienceVO) {
    return service.add(newExperienceVO);
  }

  @PostMapping("/batch")
  @Override
  public List<ExperienceVO> add(@Valid @RequestBody final List<NewExperienceVO> newExperienceVOS) {
    return service.add(newExperienceVOS);
  }

  @PutMapping("/{id}")
  @Override
  public ExperienceVO update(@PathVariable("id") final long id, @Valid @RequestBody final UpdateExperienceVO updateExperienceVO) {
    updateExperienceVO.setId(id);
    return service.update(updateExperienceVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

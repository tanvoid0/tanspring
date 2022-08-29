package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.common.template.CustomController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/achievement")
public class AchievementController implements CustomController<AchievementVO, NewAchievementVO, UpdateAchievementVO> {

  private final AchievementService service;

  public AchievementController(AchievementService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<AchievementVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public AchievementVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @Override
  public AchievementVO add(@Valid @RequestBody final NewAchievementVO newAchievementVO) {
    return service.add(newAchievementVO);
  }

  @PostMapping("/batch")
  @Override
  public List<AchievementVO> add(@Valid @RequestBody final List<NewAchievementVO> newAchievementVOS) {
    return service.add(newAchievementVOS);
  }

  @PutMapping("/{id}")
  @Override
  public AchievementVO update(@PathVariable("id") final long id, @Valid @RequestBody final UpdateAchievementVO updateAchievementVO) {
    updateAchievementVO.setId(id);
    return service.update(updateAchievementVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

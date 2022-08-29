package com.tanvoid0.tanspring.models.user.career.volunteer;

import com.tanvoid0.tanspring.common.template.CustomController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/volunteer")
public class VolunteerController implements CustomController<VolunteerVO, NewVolunteerVO, UpdateVolunteerVO> {
  private final VolunteerService service;

  public VolunteerController(VolunteerService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<VolunteerVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public VolunteerVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @Override
  public VolunteerVO add(@Valid @RequestBody final NewVolunteerVO newVolunteerVO) {
    return service.add(newVolunteerVO);
  }

  @PostMapping("/batch")
  @Override
  public List<VolunteerVO> add(@Valid @RequestBody final List<NewVolunteerVO> newVolunteerVOS) {
    return service.add(newVolunteerVOS);
  }

  @PutMapping("/{id}")
  @Override
  public VolunteerVO update(@PathVariable("id") final long id, @Valid @RequestBody final UpdateVolunteerVO updateVolunteerVO) {
    updateVolunteerVO.setId(id);
    return service.update(updateVolunteerVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

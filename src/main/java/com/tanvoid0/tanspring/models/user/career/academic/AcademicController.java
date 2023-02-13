package com.tanvoid0.tanspring.models.user.career.academic;

import com.tanvoid0.tanspring.common.template.CustomController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/academic")
@Slf4j
public class AcademicController implements CustomController<AcademicVO, NewAcademicVO, UpdateAcademicVO> {
  private final AcademicService service;

  public AcademicController(final AcademicService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<AcademicVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public AcademicVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @Override
  public AcademicVO add(@Valid @RequestBody final NewAcademicVO newAcademicVO) {
    return service.add(newAcademicVO);
  }

  @PostMapping("/batch")
  @Override
  public List<AcademicVO> add(@Valid @RequestBody final List<NewAcademicVO> newAcademicVOS) {
    return service.add(newAcademicVOS);
  }

  @PutMapping("/{id}")
  @Override
  public AcademicVO update(@PathVariable("id") final long id, @Valid @RequestBody final UpdateAcademicVO updateAcademicVO) {
    updateAcademicVO.setId(id);
    return service.update(updateAcademicVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

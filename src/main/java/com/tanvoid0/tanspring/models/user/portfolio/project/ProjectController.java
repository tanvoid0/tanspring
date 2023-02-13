package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;

import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/project")
public class ProjectController {

  final ProjectService service;


  public ProjectController(ProjectService service) {
    this.service = service;
  }

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  public List<ProjectVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public ProjectVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ProjectVO create(@Valid @RequestBody final NewProjectVO newVO) {
    return service.add(newVO);
  }

  @PutMapping("swap")
  @PreAuthorize("hasRole('USER')")
  public List<ProjectVO> swap(@Valid @RequestBody final SwapOrderSequence swapVO) {
    return service.swap(swapVO);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public ProjectVO update(@PathVariable("id") final long id, @Valid @RequestBody UpdateProjectVO updateVO) {
    updateVO.setId(id);
    return service.update(updateVO);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") final long id) {
    service.delete(id);
  }
}

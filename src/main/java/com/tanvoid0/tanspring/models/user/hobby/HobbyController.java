package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;

import lombok.extern.slf4j.Slf4j;

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
@RequestMapping("/api/v1/hobby")
@Slf4j
public class HobbyController {

  private final HobbyService service;

  public HobbyController(HobbyService service) {
    this.service = service;
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MODERATOR')")
  public List<HobbyVO> get() {
    log.info("Find all hobbies");
    return service.get();
  }

  @GetMapping("/{id}")
  public HobbyVO get(@PathVariable("id") final long id) {
    log.info("Find hobby by id {}", id);
    return service.get(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public HobbyVO add(@Valid @RequestBody final NewHobbyVO newVO) {
    log.info("Add new hobby {}", newVO);
    return service.add(newVO);
  }

  @PostMapping("/batch")
  @PreAuthorize("hasRole('USER')")
  public List<HobbyVO> add(@Valid @RequestBody final List<NewHobbyVO> newHobbyVOS) {
    return service.add(newHobbyVOS);
  }


  @PreAuthorize("hasRole('USER')")
  @PutMapping("/swap")
  public List<HobbyVO> swap(@RequestBody SwapOrderSequence swapOrderSequence) {
    return service.swap(swapOrderSequence);
  }

  @PreAuthorize("hasRole('USER')")
  @PutMapping("/{id}")
  public HobbyVO update(@PathVariable final long id, @Valid @RequestBody final UpdateHobbyVO updateVO) {
    log.info("Update hobby");
    updateVO.setId(id);
    return service.update(updateVO);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable("id") long id) {
    log.info("Delete hobby");
    return service.delete(id);
  }
}

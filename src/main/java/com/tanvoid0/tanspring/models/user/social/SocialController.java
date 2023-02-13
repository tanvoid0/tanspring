package com.tanvoid0.tanspring.models.user.social;

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
@RequestMapping("/api/v1/social")
@Slf4j
public class SocialController {
  private final SocialService service;

  public SocialController(SocialService service) {
    this.service = service;
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MODERATOR')")
  public List<SocialVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MODERATOR')")
  public SocialVO get(final @PathVariable("id") long id) {
    return service.get(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public SocialVO add(@Valid @RequestBody final NewSocialVO newSocialVO) {
    return service.add(newSocialVO);
  }

  @PostMapping("/batch")
  @PreAuthorize("hasRole('USER')")
  public List<SocialVO> add(@Valid @RequestBody final List<NewSocialVO> newSocialVOS) {
    return service.add(newSocialVOS);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public SocialVO update(@PathVariable("id") final long id, @Valid @RequestBody UpdateSocialVO updateSocialVO) {
    updateSocialVO.setId(id);
    return service.update(updateSocialVO);
  }

  @PreAuthorize("hasRole('USER')")
  @PutMapping("/swap")
  public List<SocialVO> swap(@RequestBody SwapOrderSequence swapOrderSequence) {
    return service.swap(swapOrderSequence);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  public boolean delete(@PathVariable("id") long id) {
    return service.delete(id);
  }
}

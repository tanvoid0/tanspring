package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.template.CustomController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/organization")
@Slf4j
public class OrganizationController implements CustomController<OrganizationVO, NewOrganizationVO, UpdateOrganizationVO> {

  private final OrganizationService service;

  public OrganizationController(OrganizationService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<OrganizationVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public OrganizationVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  @Override
  public OrganizationVO add(@Valid @RequestBody final NewOrganizationVO newOrganizationVO) {
    final JSONObject json = new JSONObject(newOrganizationVO);
    log.info("Creating {} with {}", json.get("_type"), json);
    return service.add(newOrganizationVO);
  }

  @Override
  public List<OrganizationVO> add(@Valid @RequestBody final List<NewOrganizationVO> newOrganizationVOS) {
    return service.add(newOrganizationVOS);
  }

  @PutMapping("/{id}")
  @Override
  public OrganizationVO update(@PathVariable("id") final long id, @Valid @RequestBody UpdateOrganizationVO updateOrganizationVO) {
    updateOrganizationVO.setId(id);
    return service.update(updateOrganizationVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

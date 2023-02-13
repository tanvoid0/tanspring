package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.common.template.CustomController;

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
@RequestMapping("/api/v1/certificate")
public class CertificateController implements CustomController<CertificateVO, NewCertificateVO, UpdateCertificateVO> {
  private final CertificateService service;

  public CertificateController(CertificateService service) {
    this.service = service;
  }

  @GetMapping
  @Override
  public List<CertificateVO> get() {
    return service.get();
  }

  @GetMapping("/{id}")
  @Override
  public CertificateVO get(@PathVariable("id") final long id) {
    return service.get(id);
  }

  @PostMapping
  @Override
  public CertificateVO add(@Valid @RequestBody final NewCertificateVO newCertificateVO) {
    return service.add(newCertificateVO);
  }

  @PostMapping("/batch")
  @Override
  public List<CertificateVO> add(@Valid @RequestBody final List<NewCertificateVO> newCertificateVOS) {
    return service.add(newCertificateVOS);
  }

  @PutMapping("/{id}")
  @Override
  public CertificateVO update(@PathVariable("id") final long id, @Valid @RequestBody final UpdateCertificateVO updateCertificateVO) {
    updateCertificateVO.setId(id);
    return service.update(updateCertificateVO);
  }

  @DeleteMapping("/{id}")
  @Override
  public boolean delete(@PathVariable("id") final long id) {
    return service.delete(id);
  }
}

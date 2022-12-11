package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {

  private final CertificateRepository repository;

  private final CareerService careerService;

  private final ModelMapper mapper;

  public CertificateServiceImpl(CertificateRepository repository, CareerService careerService, ModelMapper mapper) {
    this.repository = repository;
    this.careerService = careerService;
    this.mapper = mapper;
  }

  @Override
  public List<CertificateVO> get() {
    final Career career = careerService.findOrCreateByUser();
    final List<Certificate> list = repository.findByCareer(career);
    return list.stream().map(this::convertEntityToVO).toList();
  }

  @Override
  public CertificateVO get(final long id) {
    return convertEntityToVO(findEntity(id));
  }

  @Override
  public CertificateVO add(final NewCertificateVO newCertificateVO) {
    final Career career = careerService.findOrCreateByUser();
    Certificate entity = mapper.map(newCertificateVO, Certificate.class);
    entity.setCareer(career);
    final Certificate savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<CertificateVO> add(final List<NewCertificateVO> newCertificateVOS) {
    return newCertificateVOS.stream().map(this::add).toList();
  }

  @PutMapping("/{id}")
  @Override
  public CertificateVO update(final UpdateCertificateVO updateCertificateVO) {
    final Certificate entity = findEntity(updateCertificateVO.getId());
    mapper.map(updateCertificateVO, entity);
    final Certificate certificate = repository.save(entity);
    return convertEntityToVO(certificate);
  }

  @Override
  public boolean delete(final long id) {
    findEntity(id);
    repository.deleteById(id);
    return true;
  }

  @Override
  public Certificate findEntity(long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate", "id", id));
  }

  @Override
  public CertificateVO convertEntityToVO(Certificate certificate) {
    return mapper.map(certificate, CertificateVO.class);
  }

  @Override
  public Certificate convertVOToEntity(CertificateVO certificateVO) {
    return mapper.map(certificateVO, Certificate.class);
  }
}

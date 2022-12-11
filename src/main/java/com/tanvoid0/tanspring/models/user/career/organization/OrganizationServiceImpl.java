package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.CareerService;
import com.tanvoid0.tanspring.models.user.career.academic.Academic;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organizationService")
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

  @Autowired
  private UserService userService;

  @Autowired
  private CareerService careerService;

  @Autowired
  private OrganizationRepository repository;

  @Autowired
  private ModelMapper mapper;

  private User user;

  @Override
  public List<OrganizationVO> get() {
    return null;
  }

  @Override
  public OrganizationVO get(long id) {
    return null;
  }

  @Override
  public OrganizationVO add(final NewOrganizationVO newOrganizationVO) {
    this.user = userService.getAuthUser();

    final Career career = careerService.findOrCreateByUser(user);
    log.info("User {}, with id: {} ", user, user.getId());
    Organization entity = mapper.map(newOrganizationVO, Organization.class);
//    entity.setCareer(career);
    final Organization savedEntity = repository.save(entity);
    return convertEntityToVO(savedEntity);
  }

  @Override
  public List<OrganizationVO> add(List<NewOrganizationVO> newOrganizationVOS) {
    return newOrganizationVOS.stream().map(this::add).toList();
  }

  @Override
  public OrganizationVO update(UpdateOrganizationVO updateOrganizationVO) {
    return null;
  }

  @Override
  public boolean delete(long id) {
    return false;
  }

  @Override
  public Organization findEntity(long id) {
    return null;
  }

  @Override
  public OrganizationVO convertEntityToVO(Organization organization) {
    return null;
  }

  @Override
  public Organization convertVOToEntity(OrganizationVO organizationVO) {
    return null;
  }

  @Override
  public List<Academic> getAcademic() {
    return null;
  }

//    @Override
//    public List<Achievement> getAchievement() {
//        return null;
//    }
//
//    @Override
//    public List<Certificate> getCertificate() {
//        return null;
//    }
//
//    @Override
//    public List<Experience> getExperience() {
//        return null;
//    }
//
//    @Override
//    public List<Volunteer> getVolunteer() {
//        return null;
//    }
}

package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.models.user.career.academic.Academic;

import java.util.List;

public interface OrganizationService extends CustomService<Organization, OrganizationVO, NewOrganizationVO, UpdateOrganizationVO> {
    List<Academic> getAcademic();

//    List<Achievement> getAchievement();
//
//    List<Certificate> getCertificate();
//
//    List<Experience> getExperience();
//
//    List<Volunteer> getVolunteer();
}

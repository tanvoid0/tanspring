package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.models.user.AppUser;

public interface CareerService extends CustomService<Career, CareerVO, NewCareerVO, UpdateCareerVO> {

  CareerVO getCareer();

  Career findOrCreateByUser();

  Career findOrCreateByUser(AppUser user);

  CareerVO getByUsername(String username);
}

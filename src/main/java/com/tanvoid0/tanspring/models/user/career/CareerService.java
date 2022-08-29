package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.models.user.User;

public interface CareerService extends CustomService<Career, CareerVO, NewCareerVO, UpdateCareerVO> {
    Career findOrCreateByUser();

    Career findOrCreateByUser(User user);
}

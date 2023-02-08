package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.template.CustomService;
import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;

import java.util.List;

public interface SocialService extends CustomService<Social, SocialVO, NewSocialVO, UpdateSocialVO> {

  List<SocialVO> swap(SwapOrderSequence swapOrderSequence);
}

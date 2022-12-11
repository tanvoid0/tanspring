package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.career.CareerVO;
import com.tanvoid0.tanspring.models.user.hobby.HobbyVO;
import com.tanvoid0.tanspring.models.user.portfolio.PortfolioVO;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntityVO;
import com.tanvoid0.tanspring.models.user.social.SocialVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseVO {
  private String name;
  private String fullName;
  private String phone;
  private String avatar;
  private String coverImg;
  private int yob; // year of birth
  private String address;
  private String degree;
  private String title;
  private String titles;
  private String whatIDo;
  private String about;
  private String aboutDetailed;
  private String username;
  private String email;
  private String publicEmail;
  private String cv;
  private String url;
  //    private String password;
  private List<HobbyVO> hobbies = new ArrayList<>();
  private List<SocialVO> socials = new ArrayList<>();
  private CareerVO career = new CareerVO();
  private SkillEntityVO skill = new SkillEntityVO();
  private PortfolioVO portfolio = new PortfolioVO();
}

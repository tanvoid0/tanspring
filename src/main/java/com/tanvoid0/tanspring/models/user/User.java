package com.tanvoid0.tanspring.models.user;

//import com.tanvoid0.tanspring.models.user.career.Career;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.hobby.Hobby;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.social.Social;
import com.tanvoid0.tanspring.security.auth.Role;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"phone"})
})
public class User extends BaseEntity {
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
  @Column(length = 3000)
  private String about;
  @Column(length = 3000)
  private String aboutDetailed;
  private String username;
  private String email;
  private String publicEmail;
  private String cv;
  private String url;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<Hobby> hobbies = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<Social> socials = new HashSet<>();

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Portfolio portfolio;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Career career;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private SkillEntity skill;

}

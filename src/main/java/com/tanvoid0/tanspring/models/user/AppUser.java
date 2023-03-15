package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.models.file.FileData;
import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.hobby.Hobby;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.social.Social;
import com.tanvoid0.tanspring.security.auth.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"phone"})
})
public class AppUser implements UserDetails {
  @Serial
  private static final long serialVersionUID = -4974760917049857993L;

  @Id
  @GeneratedValue
  private Long id;

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
  @Version
  private Long version;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ToString.Exclude
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

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

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<FileData> files = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email; // TODO: Fix with username later
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

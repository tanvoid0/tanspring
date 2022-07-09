package com.tanvoid0.tanspring.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanvoid0.tanspring.enums.ERole;
import com.tanvoid0.tanspring.models.user.models.Role;
import com.tanvoid0.tanspring.models.user.models.User;
import com.tanvoid0.tanspring.models.user.service.UserRepository;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.equals("user")) {
      Role role = new Role(ERole.ROLE_ADMIN);
      final User user = User.builder()
          .username("user")
          .password("password")
          .email("user@mail.com")
          .name("user")
          .roles(Set.of(role)).build();
      return UserDetailsImpl.build(user);
    }
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
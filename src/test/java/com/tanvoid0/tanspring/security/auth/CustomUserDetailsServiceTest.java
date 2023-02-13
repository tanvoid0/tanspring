//package com.tanvoid0.tanspring.security.auth;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import com.tanvoid0.tanspring.models.user.User;
//
//import com.tanvoid0.tanspring.models.user.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {CustomUserDetailsService.class})
//@ExtendWith(SpringExtension.class)
//class CustomUserDetailsServiceTest {
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    /**
//     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
//     */
//    @Test
//    void testLoadUserByUsername() throws UsernameNotFoundException {
//        User user = new User();
//        user.setEmail("jane.doe@example.org");
//        user.setId(123L);
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setRoles(new HashSet<>());
//        user.setUsername("janedoe");
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
//        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");
//        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
//        assertTrue(actualLoadUserByUsernameResult.isEnabled());
//        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
//        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
//        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
//        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
//    }
//
//    /**
//     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
//     */
//    @Test
//    void testLoadUserByUsername3() throws UsernameNotFoundException {
//        Role role = new Role();
//        role.setId(123L);
//        role.setName("Name");
//
//        HashSet<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        User user = mock(User.class);
//        when(user.getEmail()).thenReturn("jane.doe@example.org");
//        when(user.getPassword()).thenReturn("iloveyou");
//        when(user.getRoles()).thenReturn(roleSet);
//        doNothing().when(user).setEmail((String) any());
//        doNothing().when(user).setId(anyLong());
//        doNothing().when(user).setName((String) any());
//        doNothing().when(user).setPassword((String) any());
//        doNothing().when(user).setRoles((Set<Role>) any());
//        doNothing().when(user).setUsername((String) any());
//        user.setEmail("jane.doe@example.org");
//        user.setId(123L);
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setRoles(new HashSet<>());
//        user.setUsername("janedoe");
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
//        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");
//        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
//        assertTrue(actualLoadUserByUsernameResult.isEnabled());
//        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
//        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
//        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
//        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
//        verify(user).getEmail();
//        verify(user).getPassword();
//        verify(user).getRoles();
//        verify(user).setEmail((String) any());
//        verify(user).setId(anyLong());
//        verify(user).setName((String) any());
//        verify(user).setPassword((String) any());
//        verify(user).setRoles((Set<Role>) any());
//        verify(user).setUsername((String) any());
//    }
//
//    /**
//     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
//     */
//    @Test
//    void testLoadUserByUsername4() throws UsernameNotFoundException {
//        Role role = new Role();
//        role.setId(123L);
//        role.setName("Name");
//
//        Role role1 = new Role();
//        role1.setId(123L);
//        role1.setName("Name");
//
//        HashSet<Role> roleSet = new HashSet<>();
//        roleSet.add(role1);
//        roleSet.add(role);
//        User user = mock(User.class);
//        when(user.getEmail()).thenReturn("jane.doe@example.org");
//        when(user.getPassword()).thenReturn("iloveyou");
//        when(user.getRoles()).thenReturn(roleSet);
//        doNothing().when(user).setEmail((String) any());
//        doNothing().when(user).setId(anyLong());
//        doNothing().when(user).setName((String) any());
//        doNothing().when(user).setPassword((String) any());
//        doNothing().when(user).setRoles((Set<Role>) any());
//        doNothing().when(user).setUsername((String) any());
//        user.setEmail("jane.doe@example.org");
//        user.setId(123L);
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setRoles(new HashSet<>());
//        user.setUsername("janedoe");
//        Optional<User> ofResult = Optional.of(user);
//        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
//        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");
//        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
//        assertTrue(actualLoadUserByUsernameResult.isEnabled());
//        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
//        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
//        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
//        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
//        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
//        verify(user).getEmail();
//        verify(user).getPassword();
//        verify(user).getRoles();
//        verify(user).setEmail((String) any());
//        verify(user).setId(anyLong());
//        verify(user).setName((String) any());
//        verify(user).setPassword((String) any());
//        verify(user).setRoles((Set<Role>) any());
//        verify(user).setUsername((String) any());
//    }
//
//    /**
//     * Method under test: {@link CustomUserDetailsService#loadUserByUsername(String)}
//     */
//    @Test
//    void testLoadUserByUsername5() throws UsernameNotFoundException {
//        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(Optional.empty());
//        User user = mock(User.class);
//        when(user.getEmail()).thenReturn("jane.doe@example.org");
//        when(user.getPassword()).thenReturn("iloveyou");
//        when(user.getRoles()).thenReturn(new HashSet<>());
//        doNothing().when(user).setEmail((String) any());
//        doNothing().when(user).setId(anyLong());
//        doNothing().when(user).setName((String) any());
//        doNothing().when(user).setPassword((String) any());
//        doNothing().when(user).setRoles((Set<Role>) any());
//        doNothing().when(user).setUsername((String) any());
//        user.setEmail("jane.doe@example.org");
//        user.setId(123L);
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setRoles(new HashSet<>());
//        user.setUsername("janedoe");
//        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("janedoe"));
//        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
//        verify(user).setEmail((String) any());
//        verify(user).setId(anyLong());
//        verify(user).setName((String) any());
//        verify(user).setPassword((String) any());
//        verify(user).setRoles(any());
//        verify(user).setUsername((String) any());
//    }
//}
//

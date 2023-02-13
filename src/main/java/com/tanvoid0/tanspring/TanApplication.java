package com.tanvoid0.tanspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TanApplication
//    implements CommandLineRunner
{

  public static void main(String[] args) {
    SpringApplication.run(TanApplication.class, args);
  }

//  @Bean
//  public ModelMapper modelMapper() {
//    return new ModelMapper();
//  }

//    @Autowired
//    private RoleRepository roleRepository;
//

//
//    @Override
//    public void run(String... args) throws Exception {
//        Set<String> roles = Set.of("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER");
//        findOrCreateRoles(roles);
//    }
//
//    @GetMapping("")
//    public String index() {
//        return "Tanspring running Perfectly fine.";
//    }
//
//    @GetMapping("/live/{value}")
//    public String live(
//            @PathVariable("value") String value
//    ) {
//        return "Live valuesfs: " + value;
//    }
//
//
//    public void findOrCreateRole(String name) {
//        final boolean roleExists = roleRepository.existsByName(name);
//        if (roleExists) {
//            return;
//        }
//        Role newRole = new Role();
//        newRole.setName(name);
//        roleRepository.save(newRole);
//    }
//
//    public void findOrCreateRoles(Set<String> roles) {
//        for (String role : roles) {
//            findOrCreateRole(role);
//        }
//    }

}
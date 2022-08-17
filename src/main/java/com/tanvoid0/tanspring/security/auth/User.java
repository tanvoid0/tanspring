package com.tanvoid0.tanspring.security.auth;

import lombok.Data;

import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"phone"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    private Set<Role> roles;
}

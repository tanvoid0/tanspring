package com.tanvoid0.tanspring.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private long id;
    private String name;
    private String email;
    private String password;
    private String phone;
}

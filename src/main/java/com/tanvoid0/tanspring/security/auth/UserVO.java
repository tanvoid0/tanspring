package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.models.user.hobby.Hobby;
import com.tanvoid0.tanspring.models.user.hobby.HobbyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
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
    private String about;
    private String aboutDetailed;
    private String username;
    private String email;
    private String publicEmail;
    private String cv;
    private String url;
    //    private String password;
    private Set<HobbyVO> hobbies;
}

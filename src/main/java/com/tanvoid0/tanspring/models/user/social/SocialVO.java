package com.tanvoid0.tanspring.models.user.social;

import com.tanvoid0.tanspring.common.vo.BaseUVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SocialVO {
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String url;

    private String image;

    private String icon;

    private Long orderSec;
}

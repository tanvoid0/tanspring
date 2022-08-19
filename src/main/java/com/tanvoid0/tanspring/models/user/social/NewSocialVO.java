package com.tanvoid0.tanspring.models.user.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewSocialVO {
    @NotNull
    private String title;

    @NotNull
    private String url;

    private String icon;

    private String image;
}

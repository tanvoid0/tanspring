package com.tanvoid0.tanspring.models.user.hobby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HobbyVO {
    private long id;

    @NotNull
    private String title;

    private String icon;

    private String image;

    private String orderSec;
}

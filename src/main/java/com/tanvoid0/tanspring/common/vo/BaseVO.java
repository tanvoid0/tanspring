package com.tanvoid0.tanspring.common.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseVO {
    protected long id;
    protected long version;
    protected Date createAt;
    protected Date updatedAt;
}

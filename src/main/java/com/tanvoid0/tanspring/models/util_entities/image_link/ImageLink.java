package com.tanvoid0.tanspring.models.util_entities.image_link;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_link")
public class ImageLink extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4252809191103998356L;
    
    @Column(nullable = false)
    private String title;

    private String description;

    private String url;
}

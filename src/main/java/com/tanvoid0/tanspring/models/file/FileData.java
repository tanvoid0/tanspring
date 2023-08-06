package com.tanvoid0.tanspring.models.file;

import com.tanvoid0.tanspring.common.vo.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@Entity
//@Table(name = "file_data",
//    uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"name"})
//    })
public abstract class FileData extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -7622856536078283679L;

  @Column(unique = true, nullable = false)
  protected String name;

  protected String type;

  protected String url;
}

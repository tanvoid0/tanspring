package com.tanvoid0.tanspring.models.file;
//
//import com.tanvoid0.tanspring.common.vo.BaseEntity;
//import com.tanvoid0.tanspring.models.user.AppUser;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.Lob;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//@Table(name = "file_data")
//public class DBFileData extends BaseEntity {
//
//  private String name;
//  private String type;
//  @Lob
//  @Column(name = "data", length = 1000)
//  private byte[] data;
//
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id", nullable = false)
//  private AppUser user;
//
//}

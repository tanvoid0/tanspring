//package com.tanvoid0.tanspring.models.user.skill;
//
//import com.tanvoid0.tanspring.models.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@SuperBuilder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "skill", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"title"})
//})
//public class Skill {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    private String image;
//    private String icon;
//
//    private long version = 0;
//    private Long orderSeq;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    @Column(nullable = false)
//    private Date createdAt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
//    @Column(nullable = false)
//    private Date updatedAt;
//
//    @PostPersist
//    private void postPersist() {
//        this.orderSeq = this.id;
//    }
//}

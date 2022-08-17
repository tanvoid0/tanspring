package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.models.post.Post;
import com.tanvoid0.tanspring.security.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hobbies", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})
})
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private String icon;

    private String image;

    private Long orderSec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PostPersist
    private void postPersist() {
        this.orderSec = this.id;
    }
}
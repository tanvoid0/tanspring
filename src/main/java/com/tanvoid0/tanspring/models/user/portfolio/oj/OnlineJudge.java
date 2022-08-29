package com.tanvoid0.tanspring.models.user.portfolio.oj;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;
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
@Table(name = "portfolio_online_judge", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})
})
public class OnlineJudge extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 7517821427595316551L;

    @Column(nullable = false)
    private String title;

    private String icon;

    @Column(nullable = false)
    private String progress;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;
}

package com.exlog.exlog.domain.title.entity;

import com.exlog.exlog.domain.auth.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usertitle_id", nullable = false)
    private Long usertitleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Title title;

    @CreatedDate
    @Column(name = "acquired_at", nullable = false)
    private LocalDateTime acquiredAt;

    public static UserTitle create(User user, Title title) {
        return UserTitle.builder()
                .user(user)
                .title(title)
                .build();
    }
}

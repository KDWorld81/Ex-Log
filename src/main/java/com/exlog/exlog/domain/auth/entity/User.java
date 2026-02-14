package com.exlog.exlog.domain.auth.entity;

import com.exlog.exlog.domain.title.entity.Title;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id // PK를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; // PK

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username; // 로그인한 사용자의 이름(닉네임)

    @Column(nullable = false , unique = true) // 이메일 중복 금지 unique설정
    private String email; // 로그인한 사용자의 이메일

    @Column(nullable = false)
    private String gender; // 성별

    @Builder.Default
    @Column(name = "total_exp", nullable = false)
    private Long totalExp = 0L; // 총 경험치

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private Tier tier = Tier.BRONZE; // 티어(랭크)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_title_id")
    private Title mainTitle;

    private LocalDate lastReceiveExp; // 최근 exp 증가 날짜 (exp 무한 증가 방지)

    public boolean canReceiveExp() {
        return this.lastReceiveExp == null || !this.lastReceiveExp.equals(LocalDate.now()); // 마지막 Exp 받은 날이 당일이 아닐때만 획득 가능
    }

    public void addExp(int amount) {
        this.totalExp += amount;
        this.lastReceiveExp = LocalDate.now();
        this.tier = Tier.getTierByExp(this.totalExp);
    }

}

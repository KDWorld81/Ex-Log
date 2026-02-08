package com.exlog.exlog.domain.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Tier {
    BRONZE("브론즈", 0L),
    SILVER("실버", 50L),
    GOLD("골드", 200L),
    PLATINUM("플래티넘", 500L),
    DIAMOND("다이아몬드", 1000L),
    MASTER("마스터", 2000L),
    GRANDMASTER("그랜드마스터", 3500L),
    CHALLENGER("챌린저", 6000L);

    private final String displayName;
    private final Long minExp;

    // 경험치에 맞는 티어를 탐색
    public static Tier getTierByExp(Long totalExp){
        return Arrays.stream(values())
                .filter(tier-> totalExp >= tier.minExp)
                .reduce((first,second) -> second) // 가장 높은 조건을 만족하는 티어 선택
                .orElse(BRONZE);
    }

    // 다음 티어를 찾아줌
    public Tier getNextTier(){
        int nextOrdinal = this.ordinal() + 1;
        return nextOrdinal < values().length ? values()[nextOrdinal] : null;
    }
}

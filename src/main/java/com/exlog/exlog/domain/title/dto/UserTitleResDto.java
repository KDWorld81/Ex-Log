package com.exlog.exlog.domain.title.dto;

import com.exlog.exlog.domain.title.entity.UserTitle;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor

public class UserTitleResDto {
    private final Long titleId;
    private final String titleName;
    private final String explanation;

    public static UserTitleResDto fromEntity(UserTitle userTitle) {
        return UserTitleResDto.builder()
                .titleId(userTitle.getTitle().getTitleId())
                .titleName(userTitle.getTitle().getTitleName())
                .explanation(userTitle.getTitle().getExplanation())
                .build();
    }
}

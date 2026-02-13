package com.exlog.exlog.domain.title.dto;

import com.exlog.exlog.domain.title.entity.Title;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TitleResDto {

    private final Long titleId;
    private final String titleName;
    private final String explanation;

    @JsonProperty("isAcquired")
    private final boolean isAcquired;

    public static TitleResDto fromEntity(Title title, boolean isAcquired) {
        return TitleResDto.builder()
                .titleId(title.getTitleId())
                .titleName(title.getTitleName())
                .explanation(title.getExplanation())
                .isAcquired(isAcquired)
                .build();
    }
}

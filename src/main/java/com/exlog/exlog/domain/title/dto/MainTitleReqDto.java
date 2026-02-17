package com.exlog.exlog.domain.title.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MainTitleReqDto {

    @NotNull
    private Long titleId;
}

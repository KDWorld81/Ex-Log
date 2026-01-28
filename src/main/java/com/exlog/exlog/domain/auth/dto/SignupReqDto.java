package com.exlog.exlog.domain.auth.dto;

import com.exlog.exlog.domain.auth.entity.Tier;
import com.exlog.exlog.domain.auth.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

public class SignupReqDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private final String password;

    @NotBlank(message = "이름은 필수입니다.")
    private final String username;

    @NotBlank(message = "성별을 선택해주세요.")
    private final String gender;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.getEmail())
                .password(passwordEncoder.encode(this.password))
                .username(this.username)
                .gender(this.gender)
                .totalExp(0L)
                .tier(Tier.BRONZE)
                .build();
    }
}

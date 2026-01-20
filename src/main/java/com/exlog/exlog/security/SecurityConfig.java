package com.exlog.exlog.security;

import com.exlog.exlog.security.jwt.JwtAuthenticationFilter;
import com.exlog.exlog.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt 암호화 도구를 빈으로 등록합니다. AuthService에 필요
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 비활성화: 우리는 세션이 아닌 JWT(쿠키)를 사용함
                .csrf(AbstractHttpConfigurer::disable)

                // 2. 세션 정책: 서버에서 세션을 만들지 않는 STATELESS 방식으로 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. 인가(Authorization) 규칙 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll() // 로그인, 회원가입은 누구나 허용
                        .requestMatchers("/", "/login", "/signup").permitAll() // templates 폴더의 HTML 페이지 허용
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 정적 리소스 허용
                        .anyRequest().authenticated() // 그 외의 모든 요청(운동 기록 등)은 로그인 필수
                )

                // 4. JWT 필터 배치: Filter를 시큐리티 기본 검문소에 검문
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

package com.exlog.exlog.security.jwt;

import com.exlog.exlog.security.userdetail.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. 요청 정보에서 accessToken이라는 이름의 쿠키를 탐색
        String token = resolveToken(request);

        // 2. 토큰이 존재하고 만료되지 않았는지 검사
        if (token != null && !jwtProvider.isExpired(token)) {
            try {
                // 3. 제대로된 토큰이면 안에서 유저의 이메일을 꺼냄
                String email = jwtProvider.getEmail(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 4. 스프링 시큐리티 전용 인증
                // 세 번째 인자는 권한(Role)인데, 현재는 아직 null
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // 5. 이 도장을 시큐리티 저장소에 보관 (지금부터 컨트롤러에서 누구인지 알수있음)
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("인증 성공: 유저 이메일 = {}", email);
            } catch (Exception e) {
                log.error("토큰 인증 중 에러가 발생했습니다: {}", e.getMessage());
            }
        }

        // 6. 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }

    /**
     * 쿠키 배열에서 원하는 정보를 뽑는 메서드
     */
    private String resolveToken(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> "accessToken".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}

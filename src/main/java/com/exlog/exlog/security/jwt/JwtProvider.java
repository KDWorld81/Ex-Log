package com.exlog.exlog.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    private final SecretKey secretKey;
    private final long accessExpiration;
    private final long refreshExpiration;
    private final String issuer;

    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.access-expiration}") long accessExpiration,
                       @Value("${jwt.refresh-expiration}") long refreshExpiration,
                       @Value("${jwt.issuer}") String issuer) {

        // Base64로 인코딩된 문자열을 안전하게 SecretKey 객체로 변환
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);

        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        this.issuer = issuer;
    }

    /**
     * accessToken 생성 메서드
     *
     * @param email : Token payload에 담을 정보
     * @param userId : Token payload에 담을 정보
     * @return : accessToken
     */
    public String generateAccessToken(String email, String username, Long userId) {
        return createJwt(createClaims(userId, username), email, accessExpiration);
    }

    /**
     * refreshToken 생성 메서드
     *
     * @param email : Token payload에 담을 정보
     * @param userId : Token payload에 담을 정보
     * @return : refreshToken
     */
    public String generateRefreshToken(String email, String username, Long userId) {
        return createJwt(createClaims(userId, username), email, refreshExpiration);
    }

    // 토큰의 내용(payload)에 담을 유저 정보를 맵 형태로 생성
    private Map<String, Object> createClaims(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        return claims;
    }

    // 토큰 조립
    private String createJwt(Map<String, Object> claims, String subject, Long expirationTime) {

        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    // 토큰을 읽어 내용을 리턴
    public Claims parseClaims(String token) {
        try {

            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            // 만료된 토큰이어도 내부 정보를 확인해야 할 때가 있어 에러에서 클레임을 추출
            return e.getClaims();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
    }

    public String getEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public String getUsername(String token) {
        return parseClaims(token).get("username", String.class);
    }

    public Long getUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    public boolean isExpired(String token) {
        try {
            return parseClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // 예외 발생 시 만료된 것으로 간주
        }
    }

    /**
     * 토큰의 남은 유효 시간을 밀리초 단위로 리턴합니다.
     *
     * @param token : 유효 시간을 확인할 토큰
     * @return : 남은 유효 시간 (ms)
     */
    public Long getExpiration(String token) {
        // 토큰의 만료 날짜를 가져옴
        Date expiration = parseClaims(token).getExpiration();
        // 현재 시간
        long now = new Date().getTime();
        // 남은 시간 계산
        return (expiration.getTime() - now);
    }
}
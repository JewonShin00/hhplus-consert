package com.concert_reservation.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private long expirationTime; // 만료 시간 변수

    // 기본 생성자 (10분 기본값)
    public JwtUtil() {
        this.expirationTime = 1000 * 60 * 10; // 기본 만료 시간 10분
    }

    // 만료 시간을 인자로 받는 생성자
    public JwtUtil(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    // 만료 시간 설정 메서드
    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        Date currentDate = new Date();
        Boolean expired = expirationDate.before(currentDate);
        logger.info("Current time: {}, Token expiration time: {}, Is token expired? {}", currentDate, expirationDate, expired);
        return expired;
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime); // 현재 시간에 만료 시간을 더함
        return createToken(claims, username, expirationDate);
    }

    public String createToken(Map<String, Object> claims, String subject, Date expirationDate) {
        Date issuedAt = new Date(System.currentTimeMillis());
        logger.info("Token issued at: {}", issuedAt);
        logger.info("Token will expire at: {}", expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        logger.info("Starting token validation for username: {}", username);
        try {
            final String extractedUsername = extractUsername(token);
            Boolean isValid = (extractedUsername.equals(username) && !isTokenExpired(token));
            logger.info("Token validation for username {}: {}", username, isValid);
            return isValid;
        } catch (ExpiredJwtException e) {
            logger.error("Token expired for username {}: {}", username, e.getMessage());
            throw e; // 예외를 다시 던져서 호출자에게 알리기
        } catch (Exception e) {
            logger.error("Error during token validation for username {}: {}", username, e.getMessage());
            throw e; // 예외를 다시 던져서 호출자에게 알리기
        }
    }
}

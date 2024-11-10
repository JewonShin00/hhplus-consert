package com.consertreservation.application.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    // 토큰 서명에 사용할 비밀 키. application.properties에서 주입됨
    @Value("${token.secret}")
    private String secretKey;

    // 토큰 만료 시간. application.properties에서 주입됨 (예: 24시간 등)
    @Value("${token.expiration}")
    private long expirationTime;

    /**
     * 사용자의 ID, 콘서트 ID, 대기열 순번을 포함하는 토큰을 발급하는 메서드.
     * 토큰은 만료 시간에 따라 만료됩니다.
     * @param userId 토큰을 발급받는 사용자의 ID
     * @param concertId 해당 콘서트의 ID
     * @param queuePosition 대기열 순번
     * @return 발급된 JWT 토큰 문자열
     */
    public String issueToken(String userId, Long concertId, int queuePosition) {
        // 토큰에 저장할 클레임(정보)을 설정
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);         // 사용자 ID
        claims.put("concertId", concertId);   // 콘서트 ID
        claims.put("queuePosition", queuePosition); // 대기열 순번

        // 토큰 발행 시점과 만료 시점을 설정
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        // JWT를 생성하여 반환
        return Jwts.builder()
                .setClaims(claims)               // 클레임 설정
                .setIssuedAt(now)                // 발행 시점
                .setExpiration(expiration)       // 만료 시점
                .signWith(SignatureAlgorithm.HS256, secretKey) // 서명 알고리즘과 비밀 키 설정
                .compact();                      // 토큰 문자열 생성
    }

    /**
     * 토큰을 검증하는 메서드. 유효한 토큰이면 true를, 그렇지 않으면 false를 반환.
     * @param token 검증할 토큰 문자열
     * @return 토큰이 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token) {
        try {
            // 서명 키로 토큰을 파싱하여 검증 (유효하지 않으면 예외 발생)
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;  // 검증 성공 시 true 반환
        } catch (Exception e) {
            return false; // 유효하지 않으면 false 반환
        }
    }

    /**
     * 토큰에서 userId를 추출하는 메서드.
     * @param token 사용자 ID를 추출할 토큰
     * @return 토큰에 포함된 userId 값
     */
    public String getUserIdFromToken(String token) {
        // 토큰을 파싱하여 클레임에서 userId를 추출하고 반환
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", String.class);
    }


    /**
     * JWT 토큰에서 클레임 정보를 추출합니다.
     *
     * @param token 클레임을 추출할 토큰
     * @return 토큰에서 추출한 Claims 객체
     */
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}

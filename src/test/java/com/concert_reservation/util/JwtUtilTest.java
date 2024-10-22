package com.concert_reservation.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil; // JwtUtil을 자동 주입

    @BeforeEach
    public void setUp() {
        jwtUtil.setExpirationTime(1000 * 60 * 10); // 만료 시간을 10분으로 설정
    }

    @Test
    public void testGenerateToken() {
        // 1. JWT 토큰 생성
        String token = jwtUtil.generateToken("testuser");
        assertNotNull(token);

        // 2. 생성된 토큰에서 사용자 이름 추출
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);

        // 3. 토큰이 유효한지 확인
        boolean isValid = jwtUtil.validateToken(token, "testuser");
        assertTrue(isValid);
    }

    @Test
    public void testExpiredToken() throws InterruptedException {
        // 만료 시간을 2초로 설정하여 JwtUtil 인스턴스 생성
        jwtUtil.setExpirationTime(2000); // 2초 유효 시간 설정
        String token = jwtUtil.generateToken("testuser");

        // 토큰이 유효한지 확인
        assertTrue(jwtUtil.validateToken(token, "testuser"));

        // 3초 후 토큰이 만료되었는지 확인
        Thread.sleep(3000); // 3초 대기

        // 만료된 토큰 검증
        assertThrows(ExpiredJwtException.class, () -> {
            jwtUtil.validateToken(token, "testuser");
        });
    }
}

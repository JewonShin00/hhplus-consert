package com.concert_reservation.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilIntegrationTest {

    @Autowired
    private JwtUtil jwtUtil; // JwtUtil을 자동 주입

    private String token;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class); // Logger 인스턴스 생성

    @Test
    public void testExpiredToken() {
        // 만료된 토큰 생성 (현재 시간에서 1초를 빼서 생성)
        long expiredTime = System.currentTimeMillis() - 1000; // 1초 전
        String token = jwtUtil.createToken(new HashMap<>(), "testuser", new Date(expiredTime));

        // 검증 시도
        Exception exception = assertThrows(ExpiredJwtException.class, () -> {
            jwtUtil.validateToken(token, "testuser");
        });

        String expectedMessage = "JWT expired";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

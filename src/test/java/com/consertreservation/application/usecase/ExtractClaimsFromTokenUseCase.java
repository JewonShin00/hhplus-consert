package com.consertreservation.application.usecase;

import com.consertreservation.application.service.token.TokenService;
import com.consertreservation.application.service.usecase.waitlist.ExtractClaimsFromTokenUseCase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExtractClaimsFromTokenUseCaseTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private ExtractClaimsFromTokenUseCase extractClaimsFromTokenUseCase;

    private String sampleToken;
    private Claims sampleClaims;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleToken = "sampleToken";

        // 샘플 클레임 데이터 설정
        sampleClaims = Jwts.claims().setSubject("testUserId");
        sampleClaims.put("concertId", 1L);
        sampleClaims.put("queuePosition", 5);
    }

    @Test
    @DisplayName("토큰에서 유저 ID, 콘서트 ID, 대기열 순번을 올바르게 추출하는지 테스트")
    void shouldExtractClaimsFromToken() {
        // 토큰 서비스에서 샘플 클레임 반환 설정
        when(tokenService.getClaims(sampleToken)).thenReturn(sampleClaims);

        // 클레임 추출 테스트 실행
        Claims claims = extractClaimsFromTokenUseCase.execute(sampleToken);

        // 클레임 데이터 검증
        assertEquals("testUserId", claims.getSubject());
        assertEquals(1L, claims.get("concertId", Long.class));
        assertEquals(5, claims.get("queuePosition", Integer.class));
    }
}

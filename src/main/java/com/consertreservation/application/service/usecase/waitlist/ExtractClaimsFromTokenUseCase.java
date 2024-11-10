package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.application.service.token.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class ExtractClaimsFromTokenUseCase {

    private final TokenService tokenService;

    public ExtractClaimsFromTokenUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 주어진 토큰에서 클레임을 추출하여 반환합니다.
     * @param token JWT 토큰
     * @return 토큰에 포함된 Claims 객체
     */
    public Claims execute(String token) {
        return tokenService.getClaims(token);
    }
}

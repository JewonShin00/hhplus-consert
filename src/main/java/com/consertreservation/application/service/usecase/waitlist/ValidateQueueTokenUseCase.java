package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.application.service.token.TokenService;
import org.springframework.stereotype.Service;

/**
 * 이 유즈케이스는 대기열 토큰의 유효성을 검증하는 역할을 합니다.
 * TokenService를 통해 검증 로직을 수행합니다.
 */
@Service
public class ValidateQueueTokenUseCase {

    private final TokenService tokenService;

    /**
     * TokenService 의존성 주입을 위한 생성자입니다.
     *
     * @param tokenService JWT 검증 기능을 제공하는 서비스
     */
    public ValidateQueueTokenUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 제공된 토큰의 서명과 만료 여부를 확인하여 유효성을 검증합니다.
     *
     * @param token 검증할 JWT 토큰
     * @return 토큰이 유효하면 true, 그렇지 않으면 false
     */
    public boolean execute(String token) {
        return tokenService.validateToken(token);
    }
}

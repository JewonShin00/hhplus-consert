package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.application.service.token.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

/**
 * 이 유즈케이스는 대기열 토큰에서 클레임 정보를 추출하는 역할을 합니다.
 * TokenService를 통해 JWT를 디코딩하고 클레임 정보를 가져옵니다.
 */
@Service
public class GetQueueTokenClaimsUseCase {

    private final TokenService tokenService;

    /**
     * TokenService 의존성 주입을 위한 생성자입니다.
     *
     * @param tokenService JWT 클레임 추출 기능을 제공하는 서비스
     */
    public GetQueueTokenClaimsUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 제공된 토큰에서 클레임 정보를 추출합니다.
     * 클레임에는 유저 ID, 콘서트 ID, 대기열 순번과 같은 정보가 포함됩니다.
     *
     * @param token 클레임을 추출할 JWT 토큰
     * @return 추출된 클레임 정보를 포함한 Claims 객체
     */
    public Claims execute(String token) {
        return tokenService.getClaims(token);
    }
}

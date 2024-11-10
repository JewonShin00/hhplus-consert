package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.application.service.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class IssueQueueTokenUseCase {
    private final TokenService tokenService;

    public IssueQueueTokenUseCase(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String execute(String userId, Long concertId, int queuePosition) {
        // TokenService를 사용해 유저 ID, 콘서트 ID, 대기열 순번 정보를 포함하는 토큰 생성
        return tokenService.issueToken(userId, concertId, queuePosition);
    }
}

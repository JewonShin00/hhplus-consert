package com.consertreservation.application.usecase;

import com.consertreservation.application.service.token.TokenService;
import com.consertreservation.application.service.usecase.waitlist.IssueQueueTokenUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class IssueQueueTokenUseCaseTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private IssueQueueTokenUseCase issueQueueTokenUseCase;

    private String userId;
    private Long concertId;
    private int queuePosition;
    private String sampleToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = "testUserId";
        concertId = 1L;
        queuePosition = 5;
        sampleToken = "generatedSampleToken";
    }

    @Test
    @DisplayName("유저 ID와 대기열 순번을 포함하는 토큰을 발급하는지 테스트")
    void shouldIssueTokenForUser() {
        // TokenService의 issueToken 메서드가 샘플 토큰을 반환하도록 설정
        when(tokenService.issueToken(userId, concertId, queuePosition)).thenReturn(sampleToken);

        // 토큰 발급 테스트 실행
        String token = issueQueueTokenUseCase.execute(userId, concertId, queuePosition);

        // 토큰이 null이 아니고 예상한 값인지 확인
        assertNotNull(token);
        assertEquals(sampleToken, token);
    }
}

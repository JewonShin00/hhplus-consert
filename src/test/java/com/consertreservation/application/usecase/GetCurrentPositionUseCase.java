package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.waitlist.GetCurrentPositionUseCase;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetCurrentPositionUseCaseTest {

    @Mock
    private WaitlistRepository waitlistRepository;

    @InjectMocks
    private GetCurrentPositionUseCase getCurrentPositionUseCase;

    private Long concertId;
    private String userId;
    private Waitlist waitlistEntry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        concertId = 1L;
        userId = "testUserId";

        // 샘플 대기열 엔트리 설정
        waitlistEntry = new Waitlist(concertId, userId, 5, null);
    }

    @Test
    @DisplayName("대기열에서 유저의 현재 순번을 반환하는지 테스트")
    void shouldReturnCorrectQueuePosition() {
        // 대기열 엔트리의 위치를 반환하도록 설정
        when(waitlistRepository.findByConcertIdAndUserId(concertId, userId)).thenReturn(waitlistEntry);

        // 위치 확인 테스트 실행
        int position = getCurrentPositionUseCase.execute(concertId, userId);

        // 대기열 위치가 예상대로 반환되는지 검증
        assertEquals(5, position);
    }
}

package com.consertreservation.application.usecase;

import com.consertreservation.application.service.usecase.waitlist.CheckAndEnterIfTurnUseCase;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckAndEnterIfTurnUseCaseTest {

    private CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase;
    private WaitlistRepository waitlistRepository;

    @BeforeEach
    void setUp() {
        waitlistRepository = Mockito.mock(WaitlistRepository.class);
        checkAndEnterIfTurnUseCase = new CheckAndEnterIfTurnUseCase(waitlistRepository);
    }

    @Test
    //대기열에서 첫 번째 순번인 사용자가 입장 가능한지 확인
    void testUserCanEnterIfFirstInLine() {
        // given
        Long userId = 1L;
        Waitlist mockWaitlistEntry = new Waitlist();

        // When findFirstByUserId가 호출되면 mockWaitlistEntry 반환
        when(waitlistRepository.findFirstByUserId(userId)).thenReturn(mockWaitlistEntry);
        // When isFirstInLine가 호출되면 true 반환
        when(waitlistRepository.isFirstInLine(userId)).thenReturn(true);

        // when
        boolean canEnter = checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId);

        // then
        assertTrue(canEnter, "대기열 첫 번째 사용자는 입장할 수 있어야 합니다.");

        // removeFromWaitlist 메서드가 mockWaitlistEntry로 호출되었는지 검증
        verify(waitlistRepository).removeFromWaitlist(mockWaitlistEntry);
    }

    @Test
    //대기열에서 첫 번째 순번가 아닌 사용자가 입장 가능한지 확인
    void testUserCannotEnterIfNotFirstInLine() {
        Long userId = 1L;

        // 대기열의 첫 번째가 아닌 경우
        when(waitlistRepository.isFirstInLine(userId)).thenReturn(false);

        boolean canEnter = checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId);

        assertFalse(canEnter, "대기열 첫 번째가 아닌 사용자는 입장할 수 없어야 합니다.");
    }
}

package com.consertreservation.application.facade;

import com.consertreservation.application.service.facade.WaitlistFacade;
import com.consertreservation.application.service.usecase.waitlist.CheckAndEnterIfTurnUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class WaitlistFacadeTest {

    private WaitlistFacade waitlistFacade;
    private CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase;

    @BeforeEach
    void setUp() {
        checkAndEnterIfTurnUseCase = Mockito.mock(CheckAndEnterIfTurnUseCase.class);
        waitlistFacade = new WaitlistFacade(checkAndEnterIfTurnUseCase);
    }

    @Test
    //대기열에서 첫 번째 순번인 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCanEnter() {
        Long userId = 1L;

        when(checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId)).thenReturn(true);

        boolean canEnter = waitlistFacade.checkAndEnterIfTurn(userId);

        assertTrue(canEnter, "대기열 첫 번째 사용자는 입장할 수 있어야 합니다.");
    }

    @Test
    //대기열에서 첫 번째 순번이 아닌 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCannotEnter() {
        Long userId = 1L;

        when(checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId)).thenReturn(false);

        boolean canEnter = waitlistFacade.checkAndEnterIfTurn(userId);

        assertFalse(canEnter, "대기열 첫 번째가 아닌 사용자는 입장할 수 없어야 합니다.");
    }
}

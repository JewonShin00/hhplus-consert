package com.consertreservation.application.facade;

import com.consertreservation.application.service.facade.WaitlistFacade;
import com.consertreservation.application.service.usecase.waitlist.AddToWaitlistUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckAndEnterIfTurnUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckWaitlistForSeatUseCase;
import com.consertreservation.application.service.usecase.waitlist.GetWaitlistByConcertIdUseCase;
import com.consertreservation.domain.model.Waitlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class WaitlistFacadeTest {

    @Mock
    private CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase;

    @Mock
    private AddToWaitlistUseCase addToWaitlistUseCase;

    @Mock
    private GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase;

    @Mock
    private CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase;

    @InjectMocks
    private WaitlistFacade waitlistFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    //대기열에서 첫 번째 순번인 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCanEnter() {
        String userId = "user123";

        when(checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId)).thenReturn(true);

        boolean canEnter = waitlistFacade.checkAndEnterIfTurn(userId);

        assertTrue(canEnter, "대기열 첫 번째 사용자는 입장할 수 있어야 합니다.");
    }

    @Test
    //대기열에서 첫 번째 순번이 아닌 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCannotEnter() {
        String userId = "user123";

        when(checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId)).thenReturn(false);

        boolean canEnter = waitlistFacade.checkAndEnterIfTurn(userId);

        assertFalse(canEnter, "대기열 첫 번째가 아닌 사용자는 입장할 수 없어야 합니다.");
    }
}

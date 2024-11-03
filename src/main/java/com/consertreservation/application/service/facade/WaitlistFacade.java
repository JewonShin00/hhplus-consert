package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.waitlist.AddToWaitlistUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckAndEnterIfTurnUseCase;
import com.consertreservation.application.service.usecase.waitlist.CheckWaitlistForSeatUseCase;
import com.consertreservation.application.service.usecase.waitlist.GetWaitlistByConcertIdUseCase;
import com.consertreservation.domain.model.Waitlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitlistFacade {

    private final CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase;
    private final AddToWaitlistUseCase addToWaitlistUseCase;
    private final GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase;
    private final CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase;

    public WaitlistFacade(CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase, AddToWaitlistUseCase addToWaitlistUseCase, GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase, CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase) {
        this.checkAndEnterIfTurnUseCase = checkAndEnterIfTurnUseCase;
        this.addToWaitlistUseCase = addToWaitlistUseCase;
        this.getWaitlistByConcertIdUseCase = getWaitlistByConcertIdUseCase;

        this.checkWaitlistForSeatUseCase = checkWaitlistForSeatUseCase;
    }

    public boolean checkAndEnterIfTurn(Long userId) {
        // 유즈케이스 호출
        return checkAndEnterIfTurnUseCase.checkAndEnterIfTurn(userId);
    }

    // 대기열에 추가하는 메서드
    public void addToWaitlist(Long concertId, String userId) {
        addToWaitlistUseCase.execute(concertId, userId);
    }
    // 대기열 조회
    public List<Waitlist> getWaitlist(Long concertId) {
        return getWaitlistByConcertIdUseCase.execute(concertId);
    }

    // 대기열에서 차례가 된 사용자의 좌석 확인 메서드 추가
    public String checkWaitlistForSeat(Long concertId, String userId) {
        return checkWaitlistForSeatUseCase.execute(concertId, userId);
    }
}

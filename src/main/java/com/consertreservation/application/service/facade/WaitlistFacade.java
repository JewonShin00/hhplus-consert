package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.usecase.waitlist.*;
import com.consertreservation.domain.model.Waitlist;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitlistFacade {

    private final CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase;
    private final AddToWaitlistUseCase addToWaitlistUseCase;
    private final GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase;
    private final CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase;
    private final ValidateQueueTokenUseCase validateQueueTokenUseCase;
    private final GetCurrentQueuePositionUseCase getCurrentQueuePositionUseCase;
    private final ExtractClaimsFromTokenUseCase extractClaimsFromTokenUseCase;

    public WaitlistFacade(CheckAndEnterIfTurnUseCase checkAndEnterIfTurnUseCase, AddToWaitlistUseCase addToWaitlistUseCase, GetWaitlistByConcertIdUseCase getWaitlistByConcertIdUseCase, CheckWaitlistForSeatUseCase checkWaitlistForSeatUseCase, ValidateQueueTokenUseCase validateQueueTokenUseCase, GetCurrentQueuePositionUseCase getCurrentQueuePositionUseCase, ExtractClaimsFromTokenUseCase extractClaimsFromTokenUseCase) {
        this.checkAndEnterIfTurnUseCase = checkAndEnterIfTurnUseCase;
        this.addToWaitlistUseCase = addToWaitlistUseCase;
        this.getWaitlistByConcertIdUseCase = getWaitlistByConcertIdUseCase;

        this.checkWaitlistForSeatUseCase = checkWaitlistForSeatUseCase;
        this.validateQueueTokenUseCase = validateQueueTokenUseCase;
        this.getCurrentQueuePositionUseCase = getCurrentQueuePositionUseCase;
        this.extractClaimsFromTokenUseCase = extractClaimsFromTokenUseCase;
    }

    public boolean checkAndEnterIfTurn(String userId) {
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

    // 대기열 자격 검증 메서드
    public boolean validateQueueToken(String token) {
        return validateQueueTokenUseCase.execute(token);
    }

    // 대기열의 토큰을 검증하고, 토큰에서 정보를 추출하여 현재 대기열 순번을 반환하는 메서드
    public int getQueueTokenClaims(String token) {
        // 토큰에서 클레임 추출
        Claims claims = extractClaimsFromTokenUseCase.execute(token);

        // 추출한 클레임에서 필요한 정보 추출
        String userId = claims.get("userId", String.class);
        Long concertId = claims.get("concertId", Long.class);

        // 추출한 정보를 이용해 대기열 순번 조회
        return getCurrentQueuePositionUseCase.execute(concertId, userId);
    }
}

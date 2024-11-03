package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//해당 userId가 대기열에 있는지 확인
@Service
public class CheckAndEnterIfTurnUseCase {

    private final WaitlistRepository waitlistRepository;

    public CheckAndEnterIfTurnUseCase(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    @Transactional
    public boolean checkAndEnterIfTurn(String userId) {
        // 대기열에서 해당 사용자가 첫 번째인지 확인
        Waitlist waitlistEntry = waitlistRepository.findFirstByUserId(userId);

        if (waitlistEntry != null && waitlistRepository.isFirstInLine(userId)) {
            // 사용자가 첫 번째라면, 좌석 선택 화면으로 입장 가능하도록 설정
            // 추가 로직 (예: 좌석 선택 화면으로의 입장 관련 처리)
            waitlistRepository.removeFromWaitlist(waitlistEntry.getUserId(), waitlistEntry.getConcertId());

            return true;
        }

        return false;
    }
}

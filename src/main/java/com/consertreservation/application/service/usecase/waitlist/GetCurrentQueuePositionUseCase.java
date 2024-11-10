package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentQueuePositionUseCase {

    private final WaitlistRepository waitlistRepository;

    public GetCurrentQueuePositionUseCase(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    public int execute(Long concertId, String userId) {
        Waitlist waitlistEntry = waitlistRepository.findByConcertIdAndUserId(concertId, userId);
        if (waitlistEntry == null) {
            throw new IllegalArgumentException("User not found in waitlist for concert id: " + concertId);
        }
        return waitlistEntry.getPosition();
    }
}

package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentPositionUseCase {

    private final WaitlistRepository waitlistRepository;

    public GetCurrentPositionUseCase(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    public int execute(Long concertId, String userId) {
        return waitlistRepository.findByConcertIdAndUserId(concertId, userId).getPosition();
    }
}

package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//대기열조회
@Service
public class GetWaitlistByConcertIdUseCase {
    private final WaitlistRepository waitlistRepository; // 대기열 정보를 저장할 리포지토리

    public GetWaitlistByConcertIdUseCase(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    public List<Waitlist> execute(Long concertId) {
        return waitlistRepository.findByConcertId(concertId);
    }
}

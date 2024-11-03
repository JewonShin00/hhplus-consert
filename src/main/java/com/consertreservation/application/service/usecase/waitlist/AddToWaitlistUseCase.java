package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AddToWaitlistUseCase {
    private final WaitlistRepository waitlistRepository;
    private final ConcertRepository concertRepository;

    public AddToWaitlistUseCase(WaitlistRepository waitlistRepository, ConcertRepository concertRepository) {
        this.waitlistRepository = waitlistRepository;
        this.concertRepository = concertRepository;
    }

    public void execute(Long concertId, String userId) {
        // 대기열에 사용자 추가 로직
        // 해당 콘서트가 존재하는지 확인
        if (!concertRepository.existsById(concertId)) {
            throw new IllegalArgumentException("Concert not found with id: " + concertId);
        }

        // 사용자가 이미 대기열에 등록되어 있는지 확인
        if (waitlistRepository.existsByConcertIdAndUserId(concertId, userId)) {
            throw new IllegalArgumentException("User already in waitlist for concert id: " + concertId);
        }

        // 현재 대기열 항목 수를 조회하여 position 설정
        int currentWaitlistSize = waitlistRepository.findByConcertId(concertId).size();
        int newPosition = currentWaitlistSize + 1; // 대기열 순번 설정 (1부터 시작)

        Waitlist waitlistEntry = new Waitlist(concertId, userId, newPosition, LocalDateTime.now());
        waitlistRepository.save(waitlistEntry);
    }
}
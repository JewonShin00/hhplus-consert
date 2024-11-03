package com.consertreservation.application.service.usecase.waitlist;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.domain.repository.ConcertRepository;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.domain.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 대기열에서 차례가 된 사용자의 좌석 확인 시도
@Service
public class CheckWaitlistForSeatUseCase {
    private final WaitlistRepository waitlistRepository;
    private final SeatRepository seatRepository;
    private final ConcertRepository concertRepository;
    public CheckWaitlistForSeatUseCase(WaitlistRepository waitlistRepository, SeatRepository seatRepository, ConcertRepository concertRepository) {
        this.waitlistRepository = waitlistRepository;
        this.seatRepository = seatRepository;
        this.concertRepository = concertRepository;
    }

    public String execute(Long concertId, String userId) {
        List<Waitlist> waitlist = waitlistRepository.findByConcertId(concertId);

        // 대기열에서 사용자의 순번 찾기
        Waitlist userWaitlistEntry = waitlist.stream()
                .filter(w -> w.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not in waitlist for concert id: " + concertId));

        // 사용자의 차례가 되었는지 확인
        if (userWaitlistEntry.getPosition() != 1) {
            throw new IllegalArgumentException("Not yet your turn."); // 순번이 1이 아닐 경우 예외 발생
        }

        // 여기에 예매 가능한 좌석 확인 로직 추가
        List<Seat> availableSeats = seatRepository.findByConcert_ConcertIdAndIsAvailable(concertId, true);
        if (!availableSeats.isEmpty()) {
            return "It's your turn! Please check the available seats.";
        }
        return "No available seats to reserve.";
    }


}
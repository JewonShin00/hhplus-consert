package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Waitlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitlistRepository extends JpaRepository<Waitlist, Long> {
    // 특정 콘서트에 대한 대기열 조회
    List<Waitlist> findByConcertId(Long concertId);

    // 특정 사용자 ID로 대기열 조회
    List<Waitlist> findByUserId(String userId);

    boolean existsByConcertIdAndUserId(Long concertId, String userId);

    // 사용자가 대기열에 있는지 확인하는 메서드
    Waitlist findFirstByUserId(Long userId);

    // 사용자가 대기열에서 첫 번째인지 확인하는 메서드
    boolean isFirstInLine(Long userId);

    // 대기열에서 사용자를 삭제하는 메서드
    void removeFromWaitlist(Waitlist waitlist);
}

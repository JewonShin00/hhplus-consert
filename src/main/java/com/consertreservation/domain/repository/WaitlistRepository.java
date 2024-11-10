package com.consertreservation.domain.repository;

import com.consertreservation.domain.model.Waitlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    Waitlist findFirstByUserId(String userId);

    // 사용자가 대기열에서 첫 번째인지 확인하는 메서드
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Waitlist w WHERE w.userId = :userId AND w.position = 1")
    boolean isFirstInLine(String userId);

    // 대기열에서 사용자를 삭제하는 메서드
    @Modifying
    @Transactional
    @Query("DELETE FROM Waitlist w WHERE w.userId = :userId AND w.concertId = :concertId")
    void removeFromWaitlist(@Param("userId") String userId, @Param("concertId") Long concertId);

    Waitlist findByConcertIdAndUserId(Long concertId, String userId);
}

package com.consertreservation.application.service.usecase.payment;

import com.consertreservation.domain.model.Seat;
import com.consertreservation.domain.repository.SeatRepository;
import com.consertreservation.application.service.payment.PaymentService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * PaymentUseCase 클래스는 좌석 예약 시 결제 로직을 통합하여 처리합니다.
 * 결제 요청이 성공하면 좌석 예약을 완료하고, 실패 시 예약을 취소하는 방식으로 동작합니다.
 */
@Service
public class PaymentUseCase {

    private final SeatRepository seatRepository; // 좌석 정보를 관리하는 저장소
    private final PaymentService paymentService; // 결제 처리를 담당하는 서비스

    /**
     * PaymentUseCase의 생성자.
     *
     * @param seatRepository 좌석 정보를 조회하고 업데이트하는 SeatRepository 객체
     * @param paymentService 결제 요청을 처리하는 PaymentService 객체
     */
    public PaymentUseCase(SeatRepository seatRepository, PaymentService paymentService) {
        this.seatRepository = seatRepository;
        this.paymentService = paymentService;
    }

    /**
     * 결제를 시도하고 성공 시 좌석을 예약, 실패 시 예외를 발생시킵니다.
     * @param concertId 콘서트 ID
     * @param seatNumber 좌석 번호
     * @param userId 예약자 ID
     * @param amount 결제 금액
     * @return 결제가 성공하여 좌석 예약이 완료되면 true
     * @throws IllegalStateException 결제 실패 시
     */
    public boolean execute(Long concertId, String seatNumber, String userId, double amount) {
        Seat seat = seatRepository.findByConcertIdAndSeatNumber(concertId, seatNumber)
                .orElseThrow(() -> new NoSuchElementException("Seat not found with concert_id: " + concertId + " and seat_number: " + seatNumber));

        boolean paymentSuccess = paymentService.processPayment(userId, concertId, seatNumber, amount);
        if (paymentSuccess) {
            seat.setAvailable(false);
            seat.setReservedBy(userId);
            seatRepository.save(seat);
            return true;
        } else {
            throw new IllegalStateException("Payment failed, reservation not completed.");
        }
    }
}

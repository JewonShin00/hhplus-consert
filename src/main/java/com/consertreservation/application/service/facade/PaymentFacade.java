package com.consertreservation.application.service.facade;

import com.consertreservation.application.service.payment.PaymentException;
import com.consertreservation.application.service.payment.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacade {

    private final PaymentService paymentService;

    public PaymentFacade(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 결제 요청 메서드.
     * 좌석 선택 후 결제를 진행하는 기능입니다.
     *
     * @param userId 예약을 요청하는 사용자 ID
     * @param concertId 예약하려는 콘서트 ID
     * @param seatNumber 예약하려는 좌석 번호
     * @param amount 결제 금액
     * @return 결제 완료 여부
     */
    public boolean requestPayment(String userId, Long concertId, String seatNumber, double amount) {
    // 결제를 요청하고, 결제 결과를 반환합니다.
        try {
            return paymentService.processPayment(userId, concertId, seatNumber, amount);
        } catch (PaymentException e) {
            // 결제 실패 시 로깅 및 사용자에게 실패 이유 제공
            System.err.println("결제 요청 실패: " + e.getMessage());
            return false;
        }
    }

    /**
     * 결제 취소 메서드.
     * 결제된 예약을 취소하는 기능입니다.
     *
     * @param paymentId 취소할 결제 ID
     * @return 결제 취소 여부
     */
    public boolean cancelPayment(String paymentId) {
        return paymentService.cancelPayment(paymentId);
    }

    /**
     * 결제 내역 조회 메서드.
     * 사용자 결제 내역을 조회하는 기능입니다.
     *
     * @param userId 조회할 사용자 ID
     * @return 결제 내역 정보
     */
    public String getPaymentHistory(String userId) {
        return paymentService.getPaymentHistory(userId);
    }
}

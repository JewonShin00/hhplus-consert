package com.consertreservation.application.facade.payment;

import com.consertreservation.application.service.facade.PaymentFacade;
import com.consertreservation.application.service.payment.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PaymentFacadeTest {

    // PaymentFacadeTest.java
    @DisplayName("결제 실패 시 예외 처리 테스트")
    @Test
    public void testPaymentFailure() {
        String userId = "user123";
        Long concertId = 1L;
        String seatNumber = "A1";
        double invalidAmount = -100.0; // 유효하지 않은 결제 금액

        PaymentFacade paymentFacade = new PaymentFacade(new PaymentService());

        // 결제 실패 테스트
        boolean paymentResult = paymentFacade.requestPayment(userId, concertId, seatNumber, invalidAmount);
        assertFalse(paymentResult, "결제 요청이 실패해야 합니다.");
    }

}

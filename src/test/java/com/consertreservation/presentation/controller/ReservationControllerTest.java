package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ReservationFacade;
import com.consertreservation.domain.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationFacade reservationFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("사용자 ID에 대한 예약정보가 올바르게 반환되는지 테스트")
    void testGetReservationsByUserId() throws Exception {
        // 준비
        Reservation reservation = new Reservation(); // 필요한 속성 설정
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationFacade.findReservationsByUserId("user1")).thenReturn(reservations);

        // 실행 & 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservations/user1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists()); // 추가 검증 필요
    }
}

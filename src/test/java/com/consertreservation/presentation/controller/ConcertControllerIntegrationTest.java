package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.ConcertFacade;
import com.consertreservation.domain.model.Concert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsInAnyOrder;

@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcertFacade concertFacade;

    @Test
    @DisplayName("특정 날짜에 예약 가능한 콘서트 이름을 반환_'컨트롤러-서비스' 통합테스트")
    public void getAvailableConcerts_shouldReturnConcertNamesForGivenDate() throws Exception {
        // Mocking service layer
        String date = "2024-11-01";

        // Mock된 콘서트 객체 생성
        Concert concertA = new Concert(1L, "Concert A", LocalDate.parse(date), "Location A");
        Concert concertB = new Concert(2L, "Concert B", LocalDate.parse(date), "Location B");
        Concert concertC = new Concert(3L, "Concert C", LocalDate.parse(date), "Location C");

        // Mock 설정: 특정 날짜에 세 개의 콘서트 반환
        when(concertFacade.getConcertsByDate(LocalDate.parse(date))).thenReturn(List.of(concertA, concertB, concertC));

        // Send GET request to /api/concerts/available and verify response
        mockMvc.perform(get("/api/concerts/available")
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Concert A", "Concert B", "Concert C")));
    }

    @Test
    @DisplayName("특정 날짜에 예약 가능한 콘서트 이름을 반환_'예외 처리와 에러 응답(날짜 파라미터가 없을 때)")
    void getAvailableConcerts_shouldReturnBadRequestWhenDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/concerts/available"))
                .andExpect(status().isBadRequest()); // 400 Bad Request가 발생해야 함
    }

    @Test
    @DisplayName("특정 날짜에 예약 가능한 콘서트 이름을 반환_'예외 처리와 에러 응답(잘못된 형식의 날짜)")
    void getAvailableConcerts_shouldReturnBadRequestWhenDateFormatIsInvalid() throws Exception {
        mockMvc.perform(get("/api/concerts/available").param("date", "invalid-date"))
                .andExpect(status().isBadRequest()); // 날짜 형식이 잘못되어 400 오류가 나야 함
    }
}

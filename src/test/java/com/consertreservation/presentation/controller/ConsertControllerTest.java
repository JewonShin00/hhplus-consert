package com.consertreservation.presentation.controller;

import com.consertreservation.presentation.controller.ConcertController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsInAnyOrder;

@SpringBootTest
@AutoConfigureMockMvc
class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAvailableConcerts_shouldReturnListOfConcerts() throws Exception {
        mockMvc.perform(get("/api/concerts/available")
                        .param("date", "2024-11-01"))  // 날짜 파라미터 추가
                .andExpect(status().isOk())   // 응답 상태가 200 OK인지 확인
                .andExpect(jsonPath("$[*]").isArray())  // 응답이 배열인지 확인
                .andExpect(jsonPath("$[*]", containsInAnyOrder("Concert A", "Concert B", "Concert C")));  // 예상한 콘서트 목록이 포함되어 있는지 확인
    }
}

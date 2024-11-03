package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.WaitlistFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WaitlistController.class)
class WaitlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WaitlistFacade waitlistFacade;

    @Test
        //대기열에서 첫 번째 순번인 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCanEnter() throws Exception {
        String userId = "user123";

        when(waitlistFacade.checkAndEnterIfTurn(userId)).thenReturn(true);

        mockMvc.perform(post("/api/waitlist/check-and-enter/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("입장이 가능합니다. 좌석 선택 화면으로 이동합니다."));
    }

    @Test
        //대기열에서 첫 번째 순번이 아닌 사용자가 입장 가능한지 확인
    void testCheckAndEnterIfTurnCannotEnter() throws Exception {
        String userId = "user123";

        when(waitlistFacade.checkAndEnterIfTurn(userId)).thenReturn(false);

        mockMvc.perform(post("/api/waitlist/check-and-enter/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("대기 중입니다. 입장이 불가능합니다."));
    }
}

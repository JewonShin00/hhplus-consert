package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.ConcertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
    private ConcertService concertService;

    @Test
    public void getAvailableConcerts_shouldReturnConcertNamesForGivenDate() throws Exception {
        // Mocking service layer
        String date = "2024-11-01";
        when(concertService.getAvailableConcerts(date)).thenReturn(List.of("Concert A", "Concert B", "Concert C"));

        // Send GET request to /api/concerts/available and verify response
        mockMvc.perform(get("/api/concerts/available")
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", containsInAnyOrder("Concert A", "Concert B", "Concert C")));
    }
}
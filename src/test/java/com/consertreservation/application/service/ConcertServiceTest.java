package com.consertreservation.application.service;

import com.consertreservation.domain.repository.ConcertRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ConcertServiceTest {
    @Autowired
    private MockMvc mockMvc;

//    private final ConcertService concertService = new ConcertService(concertRepository);

    @Test
    @DisplayName("예매가능한 콘서트리스트 조회_정상조회 테스트")
    void getAvailableConcerts_shouldReturnConcertList() {
//        List<String> concerts = concertService.getAvailableConcerts("2024-11-01");
//        List<String> concerts = mockMvc.
//        assertEquals(List.of("Concert A", "Concert B", "Concert C"), concerts);
    }


}

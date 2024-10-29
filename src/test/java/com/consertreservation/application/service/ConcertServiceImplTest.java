package com.consertreservation.application.service;

import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ConcertServiceImplTest {

    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertServiceImpl concertServiceimpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("특정 날짜에 예약 가능한 콘서트 이름을 반환_정상조회 테스트")
    void getAvailableConcerts_shouldReturnConcertNamesForGivenDate() {
        // given
        LocalDate testDate = LocalDate.of(2024, 11, 1);
        List<Concert> mockConcerts = List.of(
                new Concert(1L, "Concert A", testDate, "Seoul"),
                new Concert(2L, "Concert B", testDate, "Busan")
        );

        when(concertRepository.findByDate(testDate)).thenReturn(mockConcerts);

        // when
        List<Object> result = concertServiceimpl.getAvailableConcerts(testDate.toString());

        // then
        assertEquals(List.of("Concert A", "Concert B"), result);
    }
}
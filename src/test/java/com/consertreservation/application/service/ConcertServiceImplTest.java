package com.consertreservation.application.service;

import com.consertreservation.application.service.facade.ConcertFacade;
import com.consertreservation.domain.model.Concert;
import com.consertreservation.domain.repository.ConcertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ConcertServiceImplTest {

    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertFacade concertFacade;

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
        List<Object> result = Collections.singletonList(concertFacade.getConcertsByDate(testDate));

        // then
        assertEquals(List.of("Concert A", "Concert B"), result);
    }

    @Test
    @DisplayName("ID를 통한 콘서트 조회")
    void testFindById() {
        // Given
        Concert concert = new Concert(3L, "Concert C", LocalDate.of(2024, 11, 2), "Incheon");

        when(concertRepository.findById(3L)).thenReturn(Optional.of(concert));

        // When
        Optional<Concert> result = concertRepository.findById(3L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Concert C");
    }

    @Test
    @DisplayName("존재하지 않는 ID 조회시 빈 결과 반환")
    void testFindById_NotFound() {
        // When
        Optional<Concert> result = concertRepository.findById(999L);

        // Then
        assertThat(result).isNotPresent();
    }
}
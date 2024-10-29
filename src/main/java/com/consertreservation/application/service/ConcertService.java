package com.consertreservation.application.service;

import java.util.List;

public interface ConcertService {
    List<Object> getAvailableConcerts(String date); //메서드의 시그니처
}

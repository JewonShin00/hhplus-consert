package com.consertreservation.presentation.controller;

import com.consertreservation.application.service.facade.WaitlistFacade;
import com.consertreservation.domain.model.Waitlist;
import com.consertreservation.presentation.dto.ReserveSeatDTO;
import com.consertreservation.presentation.dto.SeatDTO;
import com.consertreservation.presentation.dto.WaitlistDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistController {

    private final WaitlistFacade waitlistFacade;

    public WaitlistController(WaitlistFacade waitlistFacade) {
        this.waitlistFacade = waitlistFacade;
    }

    // 좌석 확인 및 대기열 처리
    @PostMapping("/api/concerts/reserve")
    public ResponseEntity<String> reserveOrAddToWaitlist(@RequestBody ReserveSeatDTO request) {
        try {
            // 좌석 확인 및 대기열 처리
            waitlistFacade.checkWaitlistForSeat(request.getConcertId(), request.getReservedBy());
            return ResponseEntity.ok("You can now check available seats.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Seat not found with id: " + request.getSeatId());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Seat is already reserved.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while reserving the seat.");
        }
    }

    // 대기열에 추가하는 엔드포인트
    @PostMapping("/{concertId}/waitlist")
    public ResponseEntity<Void> addToWaitlist(@PathVariable Long concertId, @RequestBody WaitlistDTO waitlistDTO, @RequestBody SeatDTO seatDTO) {
        try {
            waitlistFacade.addToWaitlist(concertId, waitlistDTO.getUserId(), seatDTO.getSeatNumber());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Bad Request 400
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Internal Server Error 500
        }
    }

    // 대기열 조회
    @GetMapping("/{concertId}/waitlist")
    public ResponseEntity<List<WaitlistDTO>> getWaitlist(@PathVariable Long concertId) {
        try {
            List<Waitlist> waitlist = waitlistFacade.getWaitlist(concertId);
            if (waitlist.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            List<WaitlistDTO> waitlistDTOs = waitlist.stream()
                    .map(w -> new WaitlistDTO(w.getUserId(), w.getConcertId(), w.getPosition()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(waitlistDTOs);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Not Found 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Internal Server Error 500
        }
    }

    //대기열에 있는 사용자의 차례 알림
    @GetMapping("/api/concerts/{id}/check")
    public ResponseEntity<String> checkWaitlist(@PathVariable Long id, @RequestParam String userId) {
        try {
            waitlistFacade.checkWaitlistForSeat(id, userId);
            return ResponseEntity.ok("You can now check available seats."); // 사용자에게 알림
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Not yet your turn.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while checking your status.");
        }
    }

    @PostMapping("/check-and-enter/{userId}")
    public ResponseEntity<String> checkAndEnterIfTurn(@PathVariable String userId) {
        boolean canEnter = waitlistFacade.checkAndEnterIfTurn(userId);

        if (canEnter) {
            return ResponseEntity.ok("입장이 가능합니다. 좌석 선택 화면으로 이동합니다.");
        } else {
            return ResponseEntity.ok("대기 중입니다. 입장이 불가능합니다.");
        }
    }
}

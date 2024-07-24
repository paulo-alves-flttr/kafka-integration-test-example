package com.example.betsapi.controller;

import com.example.betsapi.kafka.model.BetData;
import com.example.betsapi.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BetController {
    private final BetService betService;

    @PostMapping("bets")
    public ResponseEntity<Void> postSendOutgoingBet(@RequestBody BetData betData) {
        betService.sendOutgoingBet(betData);
        return ResponseEntity.ok()
                .build();
    }
}

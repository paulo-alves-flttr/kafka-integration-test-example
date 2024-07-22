package com.example.betsapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("bets")
public class BetController {
    @PostMapping
    public void postSendOutgoingBet() {}
}

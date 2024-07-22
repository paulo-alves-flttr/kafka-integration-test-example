package com.example.betsapi.service;

import com.example.betsapi.kafka.config.BetTopicsProperties;
import com.example.betsapi.kafka.model.BetData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetService {
    private final BetTopicsProperties betTopicsProperties;
    private final KafkaTemplate<String, BetData> kafkaTemplate;

    public void sendOutgoingBet(BetData betData) {
        kafkaTemplate.send(betTopicsProperties.outgoingTopic(), betData.betId(), betData);
    }
}
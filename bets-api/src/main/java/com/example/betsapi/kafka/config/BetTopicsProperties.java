package com.example.betsapi.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.bets")
public record BetTopicsProperties(String outgoingTopic, String incomingTopic) {
}

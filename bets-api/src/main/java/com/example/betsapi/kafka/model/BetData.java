package com.example.betsapi.kafka.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record BetData(String betId, LocalDateTime betTime, BigDecimal totalStake, Currency currency) {
}

package com.example.betsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY, reason = "Kafka topic unavailable")
public class BetServiceException extends RuntimeException {
}

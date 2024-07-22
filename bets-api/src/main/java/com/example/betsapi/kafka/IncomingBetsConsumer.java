package com.example.betsapi.kafka;

import com.example.betsapi.kafka.model.BetData;
import com.example.betsapi.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.bets.incoming-topic}", containerFactory = "betDataListenerContainerFactory")
public class IncomingBetsConsumer {
    private final BetRepository betRepository;

    @KafkaHandler
    public void processNullMessage(@Payload(required = false) KafkaNull unused,
                                   @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("Removing bet with id:{}", key);

        betRepository.delete(key);
    }

    @KafkaHandler
    public void consume(final BetData bet) {
        log.info("Consumed bet:{}", bet);

        betRepository.save(bet);
    }
}

package com.example.integrationtests;

import com.example.betsapi.BetsApiApplication;
import com.example.betsapi.kafka.model.BetData;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Currency;
import java.util.UUID;

import static com.example.integrationtests.TestConstants.POST_SEND_OUTGOING_BET_URI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BetsApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import({KafkaTestConfiguration.class, RestConfiguration.class})
class BetsApiIntegrationTests {
    @LocalServerPort
    private int port;
    @Autowired
    @Qualifier("restTestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private Consumer<String, BetData> outgoingTopicConsumerFactory;

    @Test
    void givenBetData_whenPostSendOutgoingBet_thenExpectBetDataInKafkaOutgoingTopic() {
        final BetData betData = new BetData(UUID.randomUUID().toString(), LocalDateTime.now(),
                new BigDecimal("20.0"), Currency.getInstance("EUR"));

        postSendOutgoingBet(betData);
        checkIfBetDataIsInTopic(betData);
    }

    void postSendOutgoingBet(BetData betData) {
        final String uri = POST_SEND_OUTGOING_BET_URI.formatted(port);

        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(betData),
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    void checkIfBetDataIsInTopic(BetData betData) {
        ConsumerRecords<String, BetData> records = outgoingTopicConsumerFactory.poll(Duration.of(10, ChronoUnit.SECONDS));
        BetData betDataInTopic = null;
        for (ConsumerRecord<String, BetData> record : records) {
            if (betData.betId().equals(record.value().betId())) {
                betDataInTopic = record.value();
            }
        }

        assertNotNull(betDataInTopic);
    }
}

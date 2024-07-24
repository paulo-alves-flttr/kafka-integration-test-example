package com.example.integrationtests;

import com.example.betsapi.BetsApiApplication;
import com.example.betsapi.kafka.model.BetData;
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
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

import static com.example.integrationtests.TestConstants.POST_SEND_OUTGOING_BET_URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BetsApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import({KafkaTestConfiguration.class, RestConfiguration.class})
class BetsApiIntegrationTests {
    @LocalServerPort
    private int port;
    @Autowired
    @Qualifier("restTestTemplate")
    private RestTemplate restTemplate;

    @Test
    void givenBetData_whenPostSendOutgoingBet_thenExpectBetDataInKafkaOutgoingTopic() {
        final BetData betData = new BetData(UUID.randomUUID().toString(), LocalDateTime.now(),
                new BigDecimal("20.0"), Currency.getInstance("EUR"));
        postSendOutgoingBet(betData);


    }

    void postSendOutgoingBet(BetData betData) {
        final String uri = POST_SEND_OUTGOING_BET_URI.formatted(port);

        ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(betData),
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

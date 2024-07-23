package com.example.integrationtests;

import com.example.betsapi.BetsApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BetsApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegrationTestsApplicationTests {
    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        System.out.println("Hello World!");
    }
}

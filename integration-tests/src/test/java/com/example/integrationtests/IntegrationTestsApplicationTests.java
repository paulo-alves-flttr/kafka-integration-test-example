package com.example.integrationtests;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.betsapi.BetsApiApplication;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

@SpringBootTest(classes = BetsApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class IntegrationTestsApplicationTests {
    @ClassRule
    public static DockerComposeContainer environment = new DockerComposeContainer(
            new File("src/test/resources/docker/compose.yaml"));

    @Test
    void contextLoads() {
    }
}

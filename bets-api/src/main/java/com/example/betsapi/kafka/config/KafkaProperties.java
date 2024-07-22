package com.example.betsapi.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

@ConfigurationProperties(prefix = "spring.kafka")
record KafkaProperties(
        String bootstrapServers,
        String authUsername,
        String authPassword,
        ListenerProperties listener
) {
    public record ListenerProperties(Integer concurrency, Integer pollTimeout){}

    public Optional<String> getSaslScramAuthString() {
        if (authUsername == null || authPassword == null || "empty".equalsIgnoreCase(authUsername) || "empty".equalsIgnoreCase(authPassword)) {
            return Optional.empty();
        }

        return Optional.of(String.format(
                "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                        "username='%s' password='%s';",
                escapeQuotes(this.authUsername), escapeQuotes(this.authPassword))
        );
    }

    private static String escapeQuotes(String value) {
        return value.replace("\"", "\\\"").replace("\'", "\\\'");
    }
}

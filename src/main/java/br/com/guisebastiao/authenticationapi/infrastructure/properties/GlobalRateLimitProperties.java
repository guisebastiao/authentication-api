package br.com.guisebastiao.authenticationapi.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.rate-limit.global")
public record  GlobalRateLimitProperties(
        long capacity,
        long refillTokens,
        Duration duration
) {
}

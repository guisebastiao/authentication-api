package br.com.guisebastiao.authenticationapi.application.ratelimit;

import java.time.Duration;

public record RateLimitRule(
        long capacity,
        Duration duration
) {
}

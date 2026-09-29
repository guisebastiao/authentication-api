package br.com.guisebastiao.authenticationapi.adapter.in.dto.common;

import java.time.Duration;

public record RateLimitPolicy(
        String path,
        long capacity,
        long refillTokens,
        Duration refillDuration
) {
}

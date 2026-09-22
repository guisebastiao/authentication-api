package br.com.guisebastiao.authenticationapi.application.ratelimit;

import java.time.Duration;
import java.util.Map;

public enum RateLimitPolicy {
    SIGN_IN(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(20, Duration.ofMinutes(1)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(5, Duration.ofMinutes(1))
            )
    ),
    GOOGLE_SIGN_IN(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(20, Duration.ofMinutes(1)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(5, Duration.ofMinutes(1))
            )
    ),
    SIGN_UP(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(10, Duration.ofMinutes(10)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(3, Duration.ofMinutes(10))
            )
    ),
    RECOVER_PASSWORD_CREATE(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(10, Duration.ofMinutes(10)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(3, Duration.ofMinutes(10))
            )
    ),
    RECOVER_PASSWORD_VALIDATE(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(20, Duration.ofMinutes(5)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(5, Duration.ofMinutes(5))
            )
    ),
    RECOVER_PASSWORD_RESEND(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(10, Duration.ofMinutes(10)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(3, Duration.ofMinutes(10))
            )
    ),
    ACTIVATE_ACCOUNT(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(20, Duration.ofMinutes(5)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(5, Duration.ofMinutes(5))
            )
    ),
    ACTIVATE_ACCOUNT_RESEND(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(10, Duration.ofMinutes(10)),

                    RateLimitKeyType.EMAIL,
                    new RateLimitRule(3, Duration.ofMinutes(10))
            )
    ),
    REFRESH_TOKEN(
            Map.of(
                    RateLimitKeyType.IP,
                    new RateLimitRule(60, Duration.ofMinutes(1)),

                    RateLimitKeyType.SESSION,
                    new RateLimitRule(20, Duration.ofMinutes(1))
            )
    );

    private final Map<RateLimitKeyType, RateLimitRule> rules;

    RateLimitPolicy(Map<RateLimitKeyType, RateLimitRule> rules) {
        this.rules = rules;
    }

    public RateLimitRule getRule(RateLimitKeyType type) {
        return rules.get(type);
    }
}
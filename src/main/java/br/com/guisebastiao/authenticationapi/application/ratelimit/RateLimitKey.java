package br.com.guisebastiao.authenticationapi.application.ratelimit;

public record RateLimitKey(
        RateLimitKeyType type,
        String value
) {
    public static RateLimitKey ip(String ipAddress) {
        return new RateLimitKey(
                RateLimitKeyType.IP,
                ipAddress
        );
    }

    public static RateLimitKey email(String email) {
        return new RateLimitKey(
                RateLimitKeyType.EMAIL,
                email.trim().toLowerCase()
        );
    }

    public static RateLimitKey account(String accountId) {
        return new RateLimitKey(
                RateLimitKeyType.ACCOUNT,
                accountId
        );
    }

    public static RateLimitKey session(String sessionId) {
        return new RateLimitKey(
                RateLimitKeyType.SESSION,
                sessionId
        );
    }
}

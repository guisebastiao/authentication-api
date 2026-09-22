package br.com.guisebastiao.authenticationapi.application.result;

public record RateLimitResult(
        boolean allowed,
        long remainingAttempts,
        long retryAfterSeconds
) {
    public static RateLimitResult allowed(long remainingAttempts) {
        return new RateLimitResult(
                true,
                remainingAttempts,
                0
        );
    }

    public static RateLimitResult blocked(long retryAfterSeconds) {
        return new RateLimitResult(
                false,
                0,
                retryAfterSeconds
        );
    }
}

package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitKey;
import br.com.guisebastiao.authenticationapi.application.ratelimit.RateLimitPolicy;
import br.com.guisebastiao.authenticationapi.application.result.RateLimitResult;

public interface RateLimiterPort {
    RateLimitResult consume(RateLimitPolicy policy, RateLimitKey... keys);
}

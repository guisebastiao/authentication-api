package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;


public record IpAddress(String value) {
    private static final Pattern IPV4_PATTERN = Pattern.compile(
            "^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)" + "(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$"
    );

    public IpAddress {
        Objects.requireNonNull(value, "Ip address cannot be null");

        value = value.trim();

        if (!IPV4_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid IP address");
        }
    }
}

package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final int MAX_LENGTH = 255;

    public Email {
        Objects.requireNonNull(value, "Email cannot be null");

        value = value.trim().toLowerCase(Locale.ROOT);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The email must have a maximum of 255 characters");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}

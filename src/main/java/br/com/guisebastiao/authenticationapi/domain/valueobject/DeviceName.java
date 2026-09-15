package br.com.guisebastiao.authenticationapi.domain.valueobject;

import java.util.Locale;
import java.util.Objects;

public record DeviceName(String value) {
    private static final int MAX_LENGTH = 50;

    public DeviceName {
        value = value.trim().toLowerCase(Locale.ROOT);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The name must have a maximum of 255 characters");
        }
    }
}

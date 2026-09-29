package br.com.guisebastiao.authenticationapi.adapter.in.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import static br.com.guisebastiao.authenticationapi.adapter.in.validation.ValidationErrorCode.*;

public record CreateRecoverPasswordRequest(
        @NotBlank(message = REQUIRED)
        @Email(message = INVALID_EMAIL)
        @Length(max = 255, message = INVALID_MAX_SIZE)
        String email
) {
}

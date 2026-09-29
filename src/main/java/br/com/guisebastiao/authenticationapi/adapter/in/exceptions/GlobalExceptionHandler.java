package br.com.guisebastiao.authenticationapi.adapter.in.exceptions;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBodyError;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiFieldError;
import br.com.guisebastiao.authenticationapi.adapter.out.logger.LoggerPayload;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;
import br.com.guisebastiao.authenticationapi.application.port.out.LoggerPort;
import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;
import br.com.guisebastiao.authenticationapi.domain.exception.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final LoggerPort logger;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiBodyError<Object>> handleMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException exception,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        List<ApiFieldError> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiFieldError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        String sessionToken = request.getHeader("X-Session");

        String user = securityAccount != null ? securityAccount.account().getEmail() : null;

        LoggerPayload payload = new LoggerPayload(
                Instant.now(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                request.getRemoteAddr(),
                DomainErrorCode.VALIDATION_ERROR,
                exception.getStackTrace(),
                user,
                sessionToken
        );

        logger.warn(payload);

        ApiBodyError<Object> data = ApiBodyError.of(DomainErrorCode.VALIDATION_ERROR, fieldErrors);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(data);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiBodyError<Object>> handleDomainException(
            HttpServletRequest request,
            DomainException exception,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        String sessionToken = request.getHeader("X-Session");

        String user = securityAccount != null ? securityAccount.account().getEmail() : null;

        LoggerPayload payload = new LoggerPayload(
                Instant.now(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                exception.getValue(),
                request.getRemoteAddr(),
                exception.getCode(),
                exception.getStackTrace(),
                user,
                sessionToken
        );

        logger.warn(payload);

        ApiBodyError<Object> data = ApiBodyError.of(exception.getCode(), exception.getDetails());

        return ResponseEntity.status(exception.getValue()).body(data);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiBodyError<Void>> handleMethodNotAllowed(
            HttpServletRequest request,
            HttpRequestMethodNotSupportedException exception,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        String sessionToken = request.getHeader("X-Session");

        String user = securityAccount != null ? securityAccount.account().getEmail() : null;

        LoggerPayload payload = new LoggerPayload(
                Instant.now(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                request.getRemoteAddr(),
                DomainErrorCode.METHOD_NOT_ALLOWED,
                exception.getStackTrace(),
                user,
                sessionToken
        );

        logger.warn(payload);

        ApiBodyError<Void> data = ApiBodyError.of(DomainErrorCode.METHOD_NOT_ALLOWED, null);

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(data);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiBodyError<Void>> handleNotFound(
            HttpServletRequest request,
            NoHandlerFoundException exception,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        String sessionToken = request.getHeader("X-Session");

        String user = securityAccount != null ? securityAccount.account().getEmail() : null;

        LoggerPayload payload = new LoggerPayload(
                Instant.now(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                request.getRemoteAddr(),
                DomainErrorCode.ROUTE_NOT_FOUND,
                exception.getStackTrace(),
                user,
                sessionToken
        );

        logger.warn(payload);

        ApiBodyError<Void> data = ApiBodyError.of(DomainErrorCode.ROUTE_NOT_FOUND, null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(data);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiBodyError<Void>> handleRuntimeException(
            HttpServletRequest request,
            RuntimeException exception,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        String sessionToken = request.getHeader("X-Session");

        String user = securityAccount != null ? securityAccount.account().getEmail() : null;

        LoggerPayload payload = new LoggerPayload(
                Instant.now(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRemoteAddr(),
                DomainErrorCode.INTERNAL_SERVER_ERROR,
                exception.getStackTrace(),
                user,
                sessionToken
        );

        logger.warn(payload);

        ApiBodyError<Void> data = ApiBodyError.of(DomainErrorCode.INTERNAL_SERVER_ERROR, null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(data);
    }
}

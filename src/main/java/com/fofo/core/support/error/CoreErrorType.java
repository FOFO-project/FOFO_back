package com.fofo.core.support.error;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
public enum CoreErrorType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR);

    private final HttpStatus status;

    private final CoreErrorCode code;

    private final String message;

    private final LogLevel logLevel;

    CoreErrorType(HttpStatus status, CoreErrorCode code, String message, LogLevel logLevel) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }

}

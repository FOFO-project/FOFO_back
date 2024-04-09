package com.fofo.core.support.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoreErrorType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),
    ENUM_MAPPING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.E501, "Invalid input for code field of enum.", LogLevel.ERROR),
    INVALID_JSON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.E502, "Invalid json format error has occurred.", LogLevel.ERROR),
    INVALID_ARGUMENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.E502, "Invalid argument error has occurred.", LogLevel.ERROR);

    private final HttpStatus status;

    private final CoreErrorCode code;

    private final String message;

    private final LogLevel logLevel;

}

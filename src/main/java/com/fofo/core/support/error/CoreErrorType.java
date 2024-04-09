package com.fofo.core.support.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoreErrorType {

    // 시스템 에러
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.COME500, "An unexpected error has occurred.", LogLevel.ERROR),
    ENUM_MAPPING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.COME500, "Invalid input for code field of enum.", LogLevel.ERROR),
    INVALID_JSON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.COME502, "Invalid json format error has occurred.", LogLevel.ERROR),
    INVALID_ARGUMENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, CoreErrorCode.COME502, "Invalid argument error has occurred.", LogLevel.ERROR),

    // Member 관련 에러
    DUPLICATE_MEMBER_ERROR(HttpStatus.CONFLICT, CoreErrorCode.MEME500, "Duplicate member error has occurred", LogLevel.ERROR)
    ;

    private final HttpStatus status;

    private final CoreErrorCode code;

    private final String message;

    private final LogLevel logLevel;

}

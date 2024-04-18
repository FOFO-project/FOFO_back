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
    DUPLICATE_MEMBER_ERROR(HttpStatus.CONFLICT, CoreErrorCode.MEME500, "Duplicate member error has occurred", LogLevel.ERROR),
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, CoreErrorCode.MEME501, "Member Not found", LogLevel.ERROR),
    ADDRESS_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, CoreErrorCode.MEME502, "Address Not found", LogLevel.ERROR),

    // Member 승인 상태 관련 에러
    NOT_PENDING_FOR_DEPOSIT_ERROR(HttpStatus.BAD_REQUEST, CoreErrorCode.MEME503, "Approval status is not deposit pending status. Please check and try again.", LogLevel.ERROR),
    DEPOSIT_DATE_EXISTS_ERROR(HttpStatus.CONFLICT, CoreErrorCode.MEME504, "This member already has a deposit date.", LogLevel.ERROR),
    NOT_WAITING_FOR_APPROVE_ERROR(HttpStatus.BAD_REQUEST, CoreErrorCode.MEME505, "Approval status is not deposit complete status. Please check and try again.", LogLevel.ERROR),

    //Match 관련 에러
    MATCH_ALREADY_COMPLETED_ERROR(HttpStatus.BAD_REQUEST, CoreErrorCode.MATE500, "Match is already COMPLETED.", LogLevel.ERROR),
    MATCH_UNCANCELABLE_ERROR(HttpStatus.BAD_REQUEST, CoreErrorCode.MATE501, "UnCancelable Match exists", LogLevel.ERROR),
    MATCHABLE_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, CoreErrorCode.MATE502, "Matchable member is not found", LogLevel.ERROR),
    MATCH_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, CoreErrorCode.MATE503, "Match Not found", LogLevel.ERROR);

    private final HttpStatus status;

    private final CoreErrorCode code;

    private final String message;

    private final LogLevel logLevel;

}

package com.fofo.core.support.error;

import lombok.Getter;

@Getter
public class CoreErrorMessage {

    private final String code;

    private final String message;

    private final Object data;

    public CoreErrorMessage(CoreErrorType errorType) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
        this.data = null;
    }

    public CoreErrorMessage(CoreErrorType errorType, Object data) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
        this.data = data;
    }
}

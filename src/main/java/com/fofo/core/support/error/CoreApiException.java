package com.fofo.core.support.error;

import lombok.Getter;

@Getter
public class CoreApiException extends RuntimeException {

    private final CoreErrorType errorType;

    private final Object data;

    public CoreApiException(CoreErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = null;
    }

    public CoreApiException(CoreErrorType errorType, Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }

}

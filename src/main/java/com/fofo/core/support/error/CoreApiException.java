package com.fofo.core.support.error;

import lombok.Getter;

@Getter
public class CoreApiException extends RuntimeException {

    private final CoreErrorType errorType;

    private final Object data;

    public CoreApiException(final CoreErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = null;
    }

    public CoreApiException(final CoreErrorType errorType, final Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }

}

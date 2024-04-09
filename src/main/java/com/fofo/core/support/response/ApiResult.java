package com.fofo.core.support.response;

import com.fofo.core.support.error.CoreErrorMessage;
import com.fofo.core.support.error.CoreErrorType;
import lombok.Getter;

@Getter
public class ApiResult<S> {

    private final ResultType result;

    private final S data;

    private final CoreErrorMessage error;

    private ApiResult(final ResultType result, final S data, final CoreErrorMessage error) {
        this.result = result;
        this.data = data;
        this.error = error;
    }

    public static ApiResult<?> success() {
        return new ApiResult<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResult<S> success(final S data) {
        return new ApiResult<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResult<?> error(final CoreErrorType error) {
        return new ApiResult<>(ResultType.ERROR, null, new CoreErrorMessage(error));
    }

    public static ApiResult<?> error(final CoreErrorType error, final Object errorData) {
        return new ApiResult<>(ResultType.ERROR, null, new CoreErrorMessage(error, errorData));
    }

}

package com.fofo.core.support.response;

import com.fofo.core.support.error.CoreErrorMessage;
import com.fofo.core.support.error.CoreErrorType;
import lombok.Getter;

@Getter
public class ApiResponse<S> {

    private final ResultType result;

    private final S data;

    private final CoreErrorMessage error;

    private ApiResponse(final ResultType result, final S data, final CoreErrorMessage error) {
        this.result = result;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResponse<S> success(final S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(final CoreErrorType error) {
        return new ApiResponse<>(ResultType.ERROR, null, new CoreErrorMessage(error));
    }

    public static ApiResponse<?> error(final CoreErrorType error, final Object errorData) {
        return new ApiResponse<>(ResultType.ERROR, null, new CoreErrorMessage(error, errorData));
    }

}

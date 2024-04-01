package com.fofo.core.support.response;

import com.fofo.core.support.error.CoreErrorMessage;
import com.fofo.core.support.error.CoreErrorType;
import lombok.Getter;

@Getter
public class ApiResponse<S> {

    private final ResultType result;

    private final S data;

    private final CoreErrorMessage error;

    private ApiResponse(ResultType result, S data, CoreErrorMessage error) {
        this.result = result;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResponse<S> success(S data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> error(CoreErrorType error) {
        return new ApiResponse<>(ResultType.ERROR, null, new CoreErrorMessage(error));
    }

    public static ApiResponse<?> error(CoreErrorType error, Object errorData) {
        return new ApiResponse<>(ResultType.ERROR, null, new CoreErrorMessage(error, errorData));
    }

}

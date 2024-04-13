package com.fofo.core.support.response;

public record PageResult<T> (
    T content,
    PageInfo pageInfo
){
    public static <T> PageResult<T> of(final T content, final PageInfo pageInfo) {
        return new PageResult<>(content, pageInfo);
    }
}

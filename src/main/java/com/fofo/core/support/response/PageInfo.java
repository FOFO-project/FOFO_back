package com.fofo.core.support.response;

public record PageInfo (
        int page,
        int size,
        int totalElements,
        int totalPages
) {
    public static PageInfo of(int page, int size, int totalElements, int totalPages) {
        return new PageInfo(page, size, totalElements, totalPages);
    }

}

package com.fofo.core.support.response;

public record PageInfo (
        int page,
        int size,
        int totalElements,
        int totalPages
) {}

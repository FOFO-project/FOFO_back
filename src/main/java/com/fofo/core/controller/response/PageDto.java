package com.fofo.core.controller.response;

import com.fofo.core.support.response.PageInfo;

public record PageDto<T> (
    T content,
    PageInfo pageInfo
){}

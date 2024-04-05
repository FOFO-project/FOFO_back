package com.fofo.core.controller.request.MatchRequestDto;

import java.util.List;

public record MatchRequestDto(
        List<Long> matchIdList
) {
}

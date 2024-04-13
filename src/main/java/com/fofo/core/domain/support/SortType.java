package com.fofo.core.domain.support;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

@UtilityClass
public class SortType {

    // 입금 내림차순
    public static Sort DEFAULT_SORT_TYPE = Sort.by("depositDate").descending();
}

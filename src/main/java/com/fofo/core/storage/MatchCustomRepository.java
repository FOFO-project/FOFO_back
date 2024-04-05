package com.fofo.core.storage;

import java.util.List;

public interface MatchCustomRepository {
    long deleteMatchesBy(List<Long> matchIdList, String hold);
}

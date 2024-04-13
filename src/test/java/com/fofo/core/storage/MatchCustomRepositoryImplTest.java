package com.fofo.core.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MatchRepositoryTest {

    @Autowired MatchRepository matchRepository;
    @Test
    void findMatchPossibleMembers() {
        matchRepository.findMatchPossibleMembers();
    }
}

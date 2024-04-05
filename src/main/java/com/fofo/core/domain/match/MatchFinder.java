package com.fofo.core.domain.match;

import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.domain.member.Member;
import com.fofo.core.storage.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchFinder {

    private final MatchRepository matchRepository;

    public List<Member> findMatchPossibleMembers() {
        return null;
    }

    public Page<Match> findMatches(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return matchRepository.selectMatchResultList(pageRequest);
    }
}

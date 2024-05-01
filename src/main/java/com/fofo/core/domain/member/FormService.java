package com.fofo.core.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormFinder formFinder;

    public Form find(final long memberId) {
        return formFinder.find(memberId);
    }

    public Page<Form> findAll(final FindMember findMember, final int pageNumber, final int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return formFinder.findAll(findMember, pageRequest);
    }

}

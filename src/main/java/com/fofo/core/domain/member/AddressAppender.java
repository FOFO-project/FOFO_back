package com.fofo.core.domain.member;

import com.fofo.core.storage.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AddressAppender {

    private final AddressRepository addressRepository;

    @Transactional
    public Long append(final Address address) {
        return addressRepository.save(address.toEntity()).id();
    }

}

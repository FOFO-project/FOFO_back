package com.fofo.core.storage.converter;

import com.fofo.core.domain.match.MatchAgreement;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MatchAgreementConverter implements AttributeConverter<MatchAgreement, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(final MatchAgreement matchAgreement) {
        if(matchAgreement == null) return null;
        return matchAgreement.isCodeValue();
    }

    @Override
    public MatchAgreement convertToEntityAttribute(final Boolean code) {
        if(code == null) return null;
        return MatchAgreement.enumOfCode(code);
    }
}

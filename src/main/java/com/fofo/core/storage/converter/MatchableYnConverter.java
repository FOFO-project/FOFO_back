package com.fofo.core.storage.converter;

import com.fofo.core.domain.member.MatchableYn;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MatchableYnConverter implements AttributeConverter<MatchableYn, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(final MatchableYn attribute) {
        return attribute.isCodeValue();
    }

    @Override
    public MatchableYn convertToEntityAttribute(final Boolean code) {
        return MatchableYn.enumOfCode(code);
    }
}

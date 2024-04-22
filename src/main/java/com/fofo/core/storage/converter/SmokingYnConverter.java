package com.fofo.core.storage.converter;

import com.fofo.core.domain.member.SmokingYn;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SmokingYnConverter implements AttributeConverter<SmokingYn, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(final SmokingYn attribute) {
        return attribute.isCodeValue();
    }

    @Override
    public SmokingYn convertToEntityAttribute(final Boolean code) {
        return SmokingYn.enumOfCode(code);
    }
}

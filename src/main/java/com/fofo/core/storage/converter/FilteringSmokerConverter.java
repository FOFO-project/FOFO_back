package com.fofo.core.storage.converter;

import com.fofo.core.domain.member.FilteringSmoker;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FilteringSmokerConverter implements AttributeConverter<FilteringSmoker, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(final FilteringSmoker attribute) {
        return attribute.isCodeValue();
    }

    @Override
    public FilteringSmoker convertToEntityAttribute(final Boolean code) {
        return FilteringSmoker.enumOfCode(code);
    }
}

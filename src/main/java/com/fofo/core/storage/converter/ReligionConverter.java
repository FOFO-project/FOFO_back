package com.fofo.core.storage.converter;

import com.fofo.core.domain.member.ApprovalStatus;
import com.fofo.core.domain.member.Religion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Enum 이 DB에 저장될 때 column type이 enum type 이 아닌 String type 으로 저장되도록 하기 위한 converter
 */
@Converter
public class ReligionConverter implements AttributeConverter<Religion, String> {

    @Override
    public String convertToDatabaseColumn(final Religion attribute) {
        return attribute.codeValue();
    }

    @Override
    public Religion convertToEntityAttribute(final String code) {
        return Religion.enumOfCode(code);
    }
}

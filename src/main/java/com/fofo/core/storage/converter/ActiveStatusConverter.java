package com.fofo.core.storage.converter;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.domain.member.Religion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Enum 이 DB에 저장될 때 column type이 enum type 이 아닌 String type 으로 저장되도록 하기 위한 converter
 */
@Converter
public class ActiveStatusConverter implements AttributeConverter<ActiveStatus, String> {

    @Override
    public String convertToDatabaseColumn(final ActiveStatus attribute) {
        return attribute.codeValue();
    }

    @Override
    public ActiveStatus convertToEntityAttribute(final String code) {
        return ActiveStatus.enumOfCode(code);
    }
}

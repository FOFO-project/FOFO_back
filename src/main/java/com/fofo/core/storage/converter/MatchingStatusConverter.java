
package com.fofo.core.storage.converter;

import com.fofo.core.domain.match.MatchingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Enum 이 DB에 저장될 때 column type이 enum type 이 아닌 String type 으로 저장되도록 하기 위한 converter
 */
@Converter
public class MatchingStatusConverter implements AttributeConverter<MatchingStatus, String> {

    @Override
    public String convertToDatabaseColumn(final MatchingStatus attribute) {
        return attribute.getCodeValue();
    }

    @Override
    public MatchingStatus convertToEntityAttribute(final String code) {
        return MatchingStatus.enumOfCode(code);
    }
}

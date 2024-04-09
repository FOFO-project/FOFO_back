package com.fofo.core.storage.converter;

import com.fofo.core.domain.member.ApprovalStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Enum 이 DB에 저장될 때 column type이 enum type 이 아닌 String type 으로 저장되도록 하기 위한 converter
 */
@Converter
public class ApprovalStatusConverter implements AttributeConverter<ApprovalStatus, String> {

    @Override
    public String convertToDatabaseColumn(final ApprovalStatus attribute) {
        return attribute.codeValue();
    }

    @Override
    public ApprovalStatus convertToEntityAttribute(final String code) {
        return ApprovalStatus.enumOfCode(code);
    }
}

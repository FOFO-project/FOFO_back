package com.fofo.core.storage.converter;

import com.fofo.core.domain.image.ImageType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Enum 이 DB에 저장될 때 column type이 enum type 이 아닌 String type 으로 저장되도록 하기 위한 converter
 */
@Converter
public class ImageTypeConverter implements AttributeConverter<ImageType, String> {

    @Override
    public String convertToDatabaseColumn(final ImageType attribute) {
        return attribute.getCodeValue();
    }

    @Override
    public ImageType convertToEntityAttribute(final String code) {
        return ImageType.enumOfCode(code);
    }
}

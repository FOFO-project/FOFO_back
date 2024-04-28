package com.fofo.core.domain.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fofo.core.support.error.CoreApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.fofo.core.support.error.CoreErrorType.ENUM_MAPPING_ERROR;

@Getter
@RequiredArgsConstructor
public enum ImageType {
    USER_PROFILE("10"),
    PROFILE_CARD("20")
    ;

    private final String codeValue;

    @JsonCreator
    public static ImageType enumOfName(final String name) {
        return Arrays.stream(ImageType.values())
                .filter(v -> v.name().equals(name.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }

    public static ImageType enumOfCode(final String codeValue) {
        return Arrays.stream(ImageType.values())
                .filter(v -> v.getCodeValue().equals(codeValue))
                .findAny()
                .orElseThrow(() -> new CoreApiException(ENUM_MAPPING_ERROR));
    }
}

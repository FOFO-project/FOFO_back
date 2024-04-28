package com.fofo.core.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageService {

    private final ImageFinder imageFinder;

    public UploadFile find(final Long imageId) {
        return imageFinder.find(imageId);
    }
}

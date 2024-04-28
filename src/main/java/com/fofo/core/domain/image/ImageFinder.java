package com.fofo.core.domain.image;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.ImageRepository;
import com.fofo.core.support.error.CoreApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.fofo.core.support.error.CoreErrorType.IMAGE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ImageFinder {

    private final ImageRepository imageRepository;

    public UploadFile find(final long imageId) {
        return imageRepository.findByIdAndStatusNot(imageId, ActiveStatus.DELETED)
                .map(UploadFile::from)
                .orElseThrow(() -> new CoreApiException(IMAGE_NOT_FOUND));
    }

}

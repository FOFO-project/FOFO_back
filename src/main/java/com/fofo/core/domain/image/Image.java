package com.fofo.core.domain.image;

import com.fofo.core.domain.ActiveStatus;
import com.fofo.core.storage.MemberImageEntity;


public record Image(
        long id,
        ImageType type,
        String uploadFileName,
        String storeFileName,
        ActiveStatus status
){

    public static Image from(final MemberImageEntity entity) {
        return new Image(entity.getId(), entity.getType(), entity.getUploadFileName(), entity.getStoreFileName(), entity.getStatus());
    }
}

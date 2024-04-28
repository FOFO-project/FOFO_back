package com.fofo.core.domain.image;

import com.fofo.core.storage.MemberImageEntity;

public record UploadFile(String uploadFileName, String storeFileName) {

    public static UploadFile from(final MemberImageEntity memberImageEntity) {
        return new UploadFile(memberImageEntity.getUploadFileName(), memberImageEntity.getStoreFileName());
    }
}

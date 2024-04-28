package com.fofo.core.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(final String filename) {
        String memberId = extractMemberId(filename);
        String memberDir = fileDir + memberId + "/";
        File memberDirFile = new File(memberDir);
        if (!memberDirFile.exists()) {
            memberDirFile.mkdirs();
        }
        return memberDir + filename;
    }

    private String extractMemberId(final String originalFilename) {
        int pos = originalFilename.lastIndexOf("@");
        return originalFilename.substring(0, pos);
    }

    public List<UploadFile> storeFiles(final List<MultipartFile> multipartFiles, final long memberId) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile, memberId));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(final MultipartFile multipartFile, final long memberId) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename, memberId);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(final String originalFilename, long memberId) {
        String uploadFileName =  memberId + "@" + UUID.randomUUID();
        String ext = extractExt(originalFilename);
        return uploadFileName + "." + ext;
    }

    private String extractExt(final String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}

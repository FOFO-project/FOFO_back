package com.fofo.core.controller;

import com.fofo.core.domain.image.FileStore;
import com.fofo.core.domain.image.ImageService;
import com.fofo.core.domain.image.UploadFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Tag(name = "파일 API")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final ImageService imageService;
    private final FileStore fileStore;

    @Operation(summary = "이미지 조회")
    @GetMapping("/images/{imageId}")
    public Resource getImage(@PathVariable final long imageId) throws MalformedURLException {
        UploadFile uploadFile = imageService.find(imageId);
        return new UrlResource("file:" + fileStore.getFullPath(uploadFile.storeFileName()));
    }

    @Operation(summary = "이미지 다운로드")
    @GetMapping("/images/{imageId}/download")
    public ResponseEntity<Resource> downloadImage(@PathVariable final long imageId) throws MalformedURLException {
        UploadFile uploadFile = imageService.find(imageId);

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(uploadFile.storeFileName()));

        String encodedUploadFileName = UriUtils.encode(uploadFile.uploadFileName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}

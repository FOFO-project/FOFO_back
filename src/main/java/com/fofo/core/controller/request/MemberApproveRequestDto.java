package com.fofo.core.controller.request;

import com.fofo.core.domain.image.ImageType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(title = "가입확정 Dto")
public record MemberApproveRequestDto(
        long memberId,
        String note,
        ImageType imageType,
        @Schema(title = "멀티파트 File", description = "멀티 파트 File")
        MultipartFile imageFile
) {
}

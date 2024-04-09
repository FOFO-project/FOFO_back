package com.fofo.core.controller;

import com.fofo.core.controller.request.AppendMemberRequestDto;
import com.fofo.core.controller.response.AppendMemberResponseDto;
import com.fofo.core.domain.member.MemberService;
import com.fofo.core.support.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "맴버 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "맴버 등록 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "멤버 등록"),
            @ApiResponse(responseCode = "409", description = "멤버 중복 등록 Conflict"),
    })
    @PostMapping("/member")
    public ResponseEntity<ApiResult<AppendMemberResponseDto>> appendMember(@RequestBody @Valid final AppendMemberRequestDto request) {
        long memberId = memberService.append(request.toMember(), request.toAddress());
        return new ResponseEntity<>(ApiResult.success(new AppendMemberResponseDto(memberId)), HttpStatus.CREATED);
    }
}

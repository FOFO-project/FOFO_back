package com.fofo.core.controller;

import com.fofo.core.controller.request.AppendMemberRequestDto;
import com.fofo.core.controller.response.AppendMemberResponseDto;
import com.fofo.core.domain.member.MemberService;
import com.fofo.core.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<ApiResponse<AppendMemberResponseDto>> appendMember(@RequestBody @Valid final AppendMemberRequestDto request) {
        long memberId = memberService.append(request.toMember(), request.toAddress());
        return new ResponseEntity<>(ApiResponse.success(new AppendMemberResponseDto(memberId)), HttpStatus.OK);
    }
}

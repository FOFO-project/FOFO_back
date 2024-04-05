package com.fofo.core.controller;

import com.fofo.core.controller.request.MemberRequestDto;
import com.fofo.core.controller.response.MemberResponseDto;
import com.fofo.core.domain.member.MemberService;
import com.fofo.core.support.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<MemberResponseDto>> appendMember(@RequestBody final MemberRequestDto memberRequestDto) {
        return new ResponseEntity<>(ApiResponse.success(new MemberResponseDto(1L)), HttpStatus.OK);
    }
}

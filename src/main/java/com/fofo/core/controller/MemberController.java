package com.fofo.core.controller;

import com.fofo.core.controller.request.AppendMemberRequestDto;
import com.fofo.core.controller.request.FindMembersConditionDto;
import com.fofo.core.controller.response.AppendMemberResponseDto;
import com.fofo.core.controller.response.FindMemberResponseDto;
import com.fofo.core.domain.member.MemberService;
import com.fofo.core.support.response.ApiResult;
import com.fofo.core.support.response.PageInfo;
import com.fofo.core.support.response.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "맴버 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "맴버 등록 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "멤버 등록"),
            @ApiResponse(responseCode = "409", description = "멤버 중복 등록 Conflict")
    })
    @PostMapping("/member")
    public ResponseEntity<ApiResult<AppendMemberResponseDto>> appendMember(@RequestBody @Valid final AppendMemberRequestDto request) {
        long memberId = memberService.append(request.toMember(), request.toAddress());
        return new ResponseEntity<>(ApiResult.success(new AppendMemberResponseDto(memberId)), HttpStatus.CREATED);
    }

    @Operation(summary = "맴버 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 조회"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 멤버")
    })
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ApiResult<FindMemberResponseDto>> findMember(@PathVariable("memberId") long memberId) {
        FindMemberResponseDto response = FindMemberResponseDto.from(memberService.find(memberId));
        return new ResponseEntity<>(ApiResult.success(response), HttpStatus.OK);
    }

    @Operation(summary = "맴버 전체 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "멤버 전체 조회")
    })
    @GetMapping("/members")
    public ResponseEntity<ApiResult<PageResult<List<FindMemberResponseDto>>>> findMembers(
            @ModelAttribute("condition") final FindMembersConditionDto condition,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @Positive @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize
    ) {
        Page<FindMemberResponseDto> response = memberService.findAll(condition.toFindMember(), pageNumber, pageSize).map(FindMemberResponseDto::from);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, (int) response.getTotalElements(), response.getTotalPages());
        return new ResponseEntity<>(ApiResult.success(PageResult.of(response.getContent(), pageInfo)), HttpStatus.OK);
    }

}

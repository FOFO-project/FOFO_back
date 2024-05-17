package com.fofo.core.controller;

import com.fofo.core.controller.request.AutoMatchRequestDto;
import com.fofo.core.controller.request.ManualMatchRequestDto;
import com.fofo.core.controller.request.MatchCancelRequestDto;
import com.fofo.core.controller.request.MatchRequestDto;
import com.fofo.core.controller.request.MatchesRequestDto;
import com.fofo.core.controller.response.FailMatchIdsResponseDto;
import com.fofo.core.controller.response.MatchResponseDto;
import com.fofo.core.controller.response.MatchResultResponseDto;
import com.fofo.core.domain.match.MatchResult;
import com.fofo.core.domain.match.MatchService;
import com.fofo.core.domain.match.MatchingStatus;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "매칭 API")
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "매칭 결과 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매치 결과")
    })
    @GetMapping("/match/result")
    public ResponseEntity<ApiResult<PageResult<List<MatchResultResponseDto>>>> getMatchResult(
            @RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
            @Positive @RequestParam(value="pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "matchingStatus", required = false) MatchingStatus matchingStatus)
    {
        Page<MatchResult> matchPage = matchService.getMatchResult(pageNumber, pageSize, matchingStatus);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, (int) matchPage.getTotalElements(), matchPage.getTotalPages());
        List<MatchResultResponseDto> response = matchPage.getContent().stream()
                .map(MatchResultResponseDto::from)
                .toList();

        return new ResponseEntity<>(ApiResult.success(new PageResult<>(response, pageInfo)), HttpStatus.OK);
    }

    @Operation(summary = "전체 or 선택 자동 매치")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "자동 매치 생성"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매칭 가능 멤버")
    })
    @PostMapping("/match/auto")
    public ResponseEntity<ApiResult<MatchResponseDto>> autoMatch(@Valid @RequestBody(required = false) AutoMatchRequestDto autoMatchRequestDto){
        MatchResponseDto matchResponseDto = MatchResponseDto.of(
                matchService.autoMatch(
                        autoMatchRequestDto == null ? null : autoMatchRequestDto.memberIdList()
                )
        );
        return new ResponseEntity<>(ApiResult.success(matchResponseDto), HttpStatus.CREATED);
    }

    @Operation(summary = "수동 매치")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "수동 매치 생성"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매칭 가능 멤버")
    })
    @PostMapping("/match/manual")
    public ResponseEntity<ApiResult<?>> manualMatch(@Valid @RequestBody ManualMatchRequestDto manualMatchRequestDto){
        matchService.manualMatch(
                manualMatchRequestDto.manMemberId(),
                manualMatchRequestDto.womanMemberId()
        );
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.CREATED);
    }

    @Operation(summary = "매칭 취소")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 취소"),
            @ApiResponse(responseCode = "400", description = "취소 불가능한 매치")
    })
    @DeleteMapping("/match")
    public ResponseEntity<ApiResult<?>> cancelMatch(@Valid @RequestBody MatchCancelRequestDto matchCancelRequestDto){
        matchService.cancelMatch(matchCancelRequestDto.matchIdList());
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }

    @Operation(summary = "매칭 다음 단계")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 다음 단계"),
            @ApiResponse(responseCode = "400", description = "이미 완료된 매치"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매치")
    })
    @PostMapping("/match")
    public ResponseEntity<ApiResult<?>> matchStatusChange(@Valid @RequestBody List<MatchRequestDto> matchRequestDtoList){
        matchService.goNextMatchStep(matchRequestDtoList);
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }

    @Operation(summary = "매칭 임시저장")
    @PostMapping("/match/temporary-save")
    public ResponseEntity<ApiResult<?>> matchTemporarySave(@Valid @RequestBody List<MatchRequestDto> matchRequestDtoList){
        matchService.matchTemporarySave(matchRequestDtoList);
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }

    @Operation(summary = "성사실패 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매치완료 멤버 되돌리기 성공"),
            @ApiResponse(responseCode = "400", description = "다시 매칭될 수없는 멤버 존재")
    })
    @PostMapping("/match/meeting/fail")
    public ResponseEntity<ApiResult<FailMatchIdsResponseDto>> failmeeting(@RequestBody final MatchesRequestDto request){
        List<Long> failMatchIds = matchService.failmeeting(request.matchIdList());
        return new ResponseEntity<>(ApiResult.success(new FailMatchIdsResponseDto(failMatchIds)), HttpStatus.OK);
    }
}

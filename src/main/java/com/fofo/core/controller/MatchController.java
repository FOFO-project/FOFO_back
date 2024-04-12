package com.fofo.core.controller;

import com.fofo.core.controller.request.AutoMatchRequestDto;
import com.fofo.core.controller.request.ManualMatchRequestDto;
import com.fofo.core.controller.request.MatchCancelRequestDto;
import com.fofo.core.controller.request.MatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto;
import com.fofo.core.controller.response.PageDto;
import com.fofo.core.domain.match.MatchService;
import com.fofo.core.storage.MatchResultDto;
import com.fofo.core.support.response.ApiResult;
import com.fofo.core.support.response.PageInfo;
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
    @GetMapping("/match-result")
    public ResponseEntity<ApiResult<PageDto<List<MatchResultDto>>>> getMatchResult(
            @RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
            @Positive @RequestParam(value="pageSize", required = false, defaultValue = "20") int pageSize)
    {
        Page<MatchResultDto> matchPage = matchService.getMatchResult(pageNumber, pageSize);
        PageInfo pageInfo = new PageInfo(pageNumber, pageSize, (int) matchPage.getTotalElements(), matchPage.getTotalPages());
//        List<MatchResultResponseDto> response = matchPage.getContent().stream()
//                .map(MatchResultResponseDto::from)
//                .toList();

        return new ResponseEntity<>(ApiResult.success(new PageDto<>(matchPage.getContent(), pageInfo)), HttpStatus.OK);
    }

    @Operation(summary = "전체 or 선택 자동 매치")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "자동 매치 생성"),
            @ApiResponse(responseCode = "500", description = "Matchable member is not found")
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
            @ApiResponse(responseCode = "201", description = "수동 매치 생성")
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
            @ApiResponse(responseCode = "400", description = "취소 불가능한 매치 존재 에러")
    })
    @DeleteMapping("/match")
    public ResponseEntity<ApiResult<?>> cancelMatch(@Valid @RequestBody MatchCancelRequestDto matchCancelRequestDto){
        matchService.cancelMatch(matchCancelRequestDto.matchIdList());
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }

    @Operation(summary = "매칭 다음 단계")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 다음 단계"),
            @ApiResponse(responseCode = "400", description = "이미 완료된 매치 에러")
    })
    @PostMapping("/match")
    public ResponseEntity<ApiResult<?>> goNextMatchStep(@Valid @RequestBody MatchRequestDto matchRequestDto){
        matchService.goNextMatchStep(matchRequestDto.matchIdList(), matchRequestDto.matchingStatus());
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }
}

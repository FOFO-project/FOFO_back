package com.fofo.core.controller;

import com.fofo.core.controller.request.MatchRequestDto.AutoMatchRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.ManualMatchRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.MatchCancelRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.MatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.controller.response.PageDto;
import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.match.MatchService;
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
    public ResponseEntity<ApiResult<PageDto<List<MatchResponseDto>>>> getMatchResult(@Positive @RequestParam int page,
                                                                                     @Positive @RequestParam int size){
        Page<Match> matchPage = matchService.getMatchResult(page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) matchPage.getTotalElements(), matchPage.getTotalPages());
        List<MatchResponseDto> response = matchPage.getContent().stream()
                .map(MatchResponseDto::from)
                .toList();

        return new ResponseEntity<>(ApiResult.success(new PageDto<>(response, pageInfo)), HttpStatus.OK);
    }

    @Operation(summary = "전체 or 선택 자동 매치")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "자동 매치")
    })
    @PostMapping("/match/auto")
    public ResponseEntity<ApiResult<?>> autoMatch(@Valid @RequestBody AutoMatchRequestDto autoMatchRequestDto){
        if(autoMatchRequestDto.memberIdList() == null || autoMatchRequestDto.memberIdList().isEmpty()){
            matchService.autoMatch();
        } else{
            matchService.selectedAutoMatch(autoMatchRequestDto.memberIdList());
        }
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.CREATED);
    }

    @Operation(summary = "수동 매치")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수동 매치")
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
            @ApiResponse(responseCode = "200", description = "매칭 취소")
    })
    @DeleteMapping("/match")
    public ResponseEntity<ApiResult<?>> cancelMatch(@Valid @RequestBody MatchCancelRequestDto matchCancelRequestDto){
        matchService.cancelMatch(matchCancelRequestDto.matchIdList());
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }

    @Operation(summary = "매칭 다음 단계")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매칭 다음 단계")
    })
    @PostMapping("/match")
    public ResponseEntity<ApiResult<?>> goNextMatchStep(@Valid @RequestBody MatchRequestDto matchRequestDto){
        matchService.goNextMatchStep(matchRequestDto.matchIdList(), matchRequestDto.matchingStatus());
        return new ResponseEntity<>(ApiResult.success(), HttpStatus.OK);
    }
}

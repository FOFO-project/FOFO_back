package com.fofo.core.controller;

import com.fofo.core.controller.request.MatchRequestDto.AutoMatchRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.ManualMatchRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.MatchCancelRequestDto;
import com.fofo.core.controller.request.MatchRequestDto.MatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.controller.response.PageDto;
import com.fofo.core.domain.match.Match;
import com.fofo.core.domain.match.MatchService;
import com.fofo.core.support.response.ApiResponse;
import com.fofo.core.support.response.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @GetMapping("/match-result")
    public ResponseEntity<ApiResponse<PageDto<List<MatchResponseDto>>>> getMatchResult(@Positive @RequestParam int page,
                                                                                       @Positive @RequestParam int size){
        Page<Match> matchPage = matchService.getMatchResult(page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) matchPage.getTotalElements(), matchPage.getTotalPages());
        List<MatchResponseDto> response = MatchResponseDto.createMatchResponseDtoList(matchPage.getContent());
        return new ResponseEntity<>(ApiResponse.success(new PageDto<>(response, pageInfo)), HttpStatus.OK);
    }

    /*
    전체 자동 매치 or 선택 자동 매치
    request param(선택 자동 매치) :@RequestBody : {'memberIdList': [id1, id2, id3, ...]}
    request param(전체 자동 매치) : -
     */
    @Operation(summary = "전체 or 선택 자동 매치")
    @PostMapping("/match/auto")
    public ResponseEntity<ApiResponse<?>> autoMatch(@RequestBody AutoMatchRequestDto autoMatchRequestDto){
        matchService.autoMatch();
        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
    }

    @Operation(summary = "수동 매치")
    @PostMapping("/match/manual")
    public ResponseEntity<ApiResponse<?>> manualMatch(@RequestBody ManualMatchRequestDto manualMatchRequestDto){
        matchService.manualMatch(manualMatchRequestDto);
        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
    }

    @Operation(summary = "매칭 취소")
    @DeleteMapping("/match")
    public ResponseEntity<ApiResponse<?>> cancelMatch(@RequestBody MatchCancelRequestDto matchCancelRequestDto){
        matchService.cancelMatch(matchCancelRequestDto.matchIdList());
        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
    }

    /*
    프로필 발송(매칭 대기 -> 매칭 중)
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...]}
    response : status code
     */
//    @PostMapping("matchi")
//    public ResponseEntity<ApiResponse<?>> sendProfile(@RequestBody MatchRequestDto matchRequestDto){
//        matchService.sendProfile(matchRequestDto.matchIdList());
//        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
//    }

    /*
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...], 'matchingStatus':''}
    response : status code
     */
    @Operation(summary = "매칭 다음 단계")
    @PostMapping("/match")
    public ResponseEntity<ApiResponse<?>> goNextMatchStep(@RequestBody MatchRequestDto matchRequestDto){
        matchService.confirmMatch(matchRequestDto.matchIdList());
        return new ResponseEntity<>(ApiResponse.success(), HttpStatus.OK);
    }
}

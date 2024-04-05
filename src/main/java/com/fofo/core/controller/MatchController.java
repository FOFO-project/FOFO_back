package com.fofo.core.controller;

import com.fofo.core.controller.request.MatchRequestDto.MatchRequestDto;
import com.fofo.core.controller.response.MatchResponseDto.MatchResponseDto;
import com.fofo.core.domain.match.MatchService;
import com.fofo.core.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    /*
    매치 결과 조회
    GET /match-result
    request param : -
    response : {'data': [CoupleDto1, CoupleDto2, CoupleDto3, ...]}
     */
    public ApiResponse<List<MatchResponseDto>> getMatchResult(){
//        List<CoupleDto> = matchService.getMatchResult();
        return ApiResponse.success(new ArrayList<MatchResponseDto>());
    }

    /*
    자동 매치 or 관리자 스페셜 매치
    POST /match/auto
    request param(관리자 스페셜 매치) :@RequestBody : {'memberIdList': [id1, id2, id3, ...]}
    request param(자동 매치) : -
    response : status code
     */
    public ApiResponse<?> autoMatch(@RequestBody MatchRequestDto matchRequestDto){
        matchService.autoMatch();
        return ApiResponse.success();
    }

    /*
    수동 매치
    POST /match/manual
    request param : @RequestBody : {'memberIdList': [id1, id2]}
    response : status code
     */
    public ApiResponse<?> manualMatch(@RequestBody MatchRequestDto matchRequestDto){
        return ApiResponse.success();
    }

    /*
    매칭 취소
    DELETE /match
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...]}
    response : status code
     */
    public ApiResponse<?> cancelMatch(@RequestBody MatchRequestDto matchRequestDto){
        matchService.cancelMatch(matchRequestDto.matchIdList());
        return ApiResponse.success();
    }

    /*
    매칭 확정
    POST /match
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...]}
    response : status code
     */
    public ApiResponse<?> confirmMatch(@RequestBody MatchRequestDto matchRequestDto){
        matchService.confirmMatch(matchRequestDto.matchIdList());
        return ApiResponse.success();
    }
}

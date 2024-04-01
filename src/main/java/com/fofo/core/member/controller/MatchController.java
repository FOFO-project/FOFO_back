package com.fofo.core.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    /*
    매치 결과 조회
    GET /match-result
    request param : -
    response : {'data': [CoupleDto1, CoupleDto2, CoupleDto3, ...]}
     */
    public ResponseEntity<Map<String, Object>> getMatchResult(){
        Map<String, Object> result  = new HashMap<>();
        return ResponseEntity.ok(result);
    }

    /*
    자동 매치 or 관리자 스페셜 매치
    POST /match/auto
    request param(관리자 스페셜 매치) :@RequestBody : {'memberIdList': [id1, id2, id3, ...]}
    request param(자동 매치) : -
    response : status code
     */
    public ResponseEntity<Void> autoMatch(){
        return ResponseEntity.ok().build();
    }

    /*
    수동 매치
    POST /match/manual
    request param : @RequestBody : {'memberIdList': [id1, id2]}
    response : status code
     */
    public ResponseEntity<Void> manualMatch(){
        return ResponseEntity.ok().build();
    }

    /*
    매칭 취소
    DELETE /match
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...]}
    response : status code
     */
    public ResponseEntity<Void> cancelMatch(){
        return ResponseEntity.ok().build();
    }

    /*
    매칭 확정
    POST /match
    request param : @RequestBody : {'matchIdList': [id1, id2, id3, ...]}
    response : status code
     */
    public ResponseEntity<Void> confirmMatch(){
        return ResponseEntity.ok().build();
    }
}

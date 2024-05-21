package com.fofo.core.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Profile확인 () {
        //when
        String profile = this.restTemplate.getForObject("/profile", String.class);

        //then
        Assertions.assertThat(profile).isEqualTo("local");
    }
}

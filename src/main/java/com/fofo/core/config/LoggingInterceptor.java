package com.fofo.core.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String contentType = request.getHeader("Content-Type");
        String accept = request.getHeader("Accept");
        String clientIP = request.getRemoteAddr();

        log.info("[preHandle] Request URI: {}, Content-Type: {}, Accept: {}, Client IP: {}", requestURI, contentType, accept, clientIP);

        return true;
    }
}

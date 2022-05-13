package com.bithumb.tide.engine.controller;

import com.bithumb.tide.engine.service.AsyncTaskService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncTaskController {

    private final AsyncTaskService asyncTaskService;

    @PostMapping("/task/publish")
    public ResponseEntity publishAsyncTask(@RequestBody JsonNode request) {
        log.info(String.valueOf(request));
        String result = asyncTaskService.publishTask(getRequestPath(), request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private String getRequestPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("async-request-path");
    }
}

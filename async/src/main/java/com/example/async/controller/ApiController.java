package com.example.async.controller;

import com.example.async.service.AnsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private final AnsyncService ansyncService;

    public ApiController(AnsyncService ansyncService) {
        this.ansyncService = ansyncService;
    }

    @GetMapping("/hello")
    public CompletableFuture hello(){
        log.info("Completable Future init");
        return ansyncService.run();
    }
    //CompletableFuture 다른 thread에서 실행시키고 결과를 반환 받는 형태
}

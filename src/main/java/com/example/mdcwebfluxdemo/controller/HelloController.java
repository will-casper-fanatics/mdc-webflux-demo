package com.example.mdcwebfluxdemo.controller;

import com.example.mdcwebfluxdemo.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    private final HelloService helloService;

    @GetMapping
    public Mono<ResponseEntity<String>> hello() {
        // generate random "userId" for demonstration purposes
        String userId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put("userId", userId);

        return Mono.just("")
                .doOnNext(s -> log.info("Entered HelloController.hello at {}", LocalDateTime.now()))
                .delayElement(Duration.ofMillis(10))
                .flatMap(r -> this.helloService.hello())
                .delayElement(Duration.ofMillis(10))
                .doOnNext(s -> log.info("Exited HelloController.hello at {}", LocalDateTime.now()))
                .map(ResponseEntity::ok);
    }

}

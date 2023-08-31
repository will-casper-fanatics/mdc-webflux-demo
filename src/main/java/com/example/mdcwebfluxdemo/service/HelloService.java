package com.example.mdcwebfluxdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
public class HelloService {
    public Mono<String> hello() {
        log.info("Entered HelloService.hello at {}", LocalDateTime.now());
        return Mono.just("Hello World")
                .delayElement(Duration.ofMillis(10))
                .doOnNext(s -> log.info("Exited HelloService.hello at {}", LocalDateTime.now()));
    }
}

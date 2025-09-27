package org.example.api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class PingController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping({"/ping"})
    public Ping greeting() {
        return new Ping(counter.incrementAndGet(), "pong", "ok", Instant.now().toString());
    }
}

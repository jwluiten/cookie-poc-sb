package com.luiten.cookiepoc.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import static com.luiten.cookiepoc.utilities.CookieBuilder.buildCookie;

@Controller
public class PageController {

    @GetMapping
    Mono<String> home() {
        return Mono.just("home");
    }

    @GetMapping(value = "/page2")
    Mono<Rendering> page2() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, buildCookie("page2", "from page 2", 23 * 60 * 60 * 1000).toString());
        return Mono.just(
            Rendering
                .view("page2")
                .status(HttpStatus.OK)
                .headers(headers)
                .build());
    }
}

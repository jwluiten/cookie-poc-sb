package com.luiten.cookiepoc.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.luiten.cookiepoc.utilities.CookieBuilder.buildCookie;

@Log4j2
@RestController
@OpenAPIDefinition(
    info = @Info(
        title = "Cookie POC controller",
        version = "1.0"
    )
)
public class RedirectController {

    @GetMapping(path = "/redirect")
    @Operation(
        summary = "Redirection endpoint",
        responses = {
            @ApiResponse(responseCode = "302", description = "redirect to requested URL")
        }
    )
    public Mono<ResponseEntity<Void>> getRedirect(
        @RequestParam("url") String url
    ) {
        return Mono.just(ResponseEntity
            .status(HttpStatus.FOUND)
            .headers(headers -> headers
                .add(HttpHeaders.LOCATION, url))
            .headers(headers -> headers
                .add(HttpHeaders.SET_COOKIE, buildCookie("cxrsProcessed", "true",60000).toString()))
            .headers(headers -> headers
                .add(HttpHeaders.SET_COOKIE, buildCookie("survivor", "kilroy",24* 60* 60 * 1000).toString()))
            .build());
    }
}

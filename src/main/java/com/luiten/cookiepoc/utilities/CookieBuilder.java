package com.luiten.cookiepoc.utilities;

import org.springframework.http.ResponseCookie;

import java.time.Duration;

public class CookieBuilder {
    public static ResponseCookie buildCookie(String name, String value, int durationInMillis) {
        return ResponseCookie
            .from(name, "value")
            .path("/")
            .maxAge(Duration.ofMillis(durationInMillis))
            .httpOnly(true)
            .build();
    }

}

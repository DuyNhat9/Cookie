package com.example.cookie_demo;

import java.time.Duration;
import java.time.Instant;

public class Cookie {
    private String name;
    private String value;
    private Instant expirationTime;
    private boolean secure;
    private boolean httpOnly;
    private String sameSite;

    public Cookie(String name, String value, Duration maxAge, boolean secure, boolean httpOnly, String sameSite) {
        this.name = name;
        this.value = value;
        this.expirationTime = Instant.now().plus(maxAge);
        this.secure = secure;
        this.httpOnly = httpOnly;
        this.sameSite = sameSite;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Instant getExpirationTime() {
        return expirationTime;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expirationTime);
    }

    public boolean isSecure() {
        return secure;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public String getSameSite() {
        return sameSite;
    }
}
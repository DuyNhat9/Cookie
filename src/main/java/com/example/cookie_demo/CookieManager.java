package com.example.cookie_demo;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CookieManager {
    private Map<String, Map<String, Cookie>> cookies; // Lưu trữ cookie theo tên miền

    public CookieManager() {
        this.cookies = new HashMap<>();
    }

    public void setCookie(String domain, String name, String value, Duration maxAge, boolean secure, boolean httpOnly, String sameSite) {
        if (!cookies.containsKey(domain)) {
            cookies.put(domain, new HashMap<>());
        }
        Cookie cookie = new Cookie(name, value, maxAge, secure, httpOnly, sameSite);
        cookies.get(domain).put(name, cookie);
    }

    public Cookie getCookie(String domain, String name) {
        if (cookies.containsKey(domain) && cookies.get(domain).containsKey(name)) {
            return cookies.get(domain).get(name);
        }
        return null;
    }

    public void deleteCookie(String domain, String name) {
        if (cookies.containsKey(domain)) {
            cookies.get(domain).remove(name);
        }
    }

    public Map<String, Map<String, Cookie>> listCookies() {
        return new HashMap<>(cookies);
    }

    public void clearCookies() {
        cookies.clear();
    }

    public void clearCookies(String domain) {
        if (cookies.containsKey(domain)) {
            cookies.get(domain).clear();
        }
    }

    // Các phương thức test
    public void testSetCookie(String domain, String name, String value, Duration maxAge, boolean secure, boolean httpOnly, String sameSite) {
        setCookie(domain, name, value, maxAge, secure, httpOnly, sameSite);
        Cookie testCookie = getCookie(domain, name);
        assert testCookie != null;
        assert testCookie.getValue().equals(value);
        assert !testCookie.isExpired();
        assert testCookie.isSecure() == secure;
        assert testCookie.isHttpOnly() == httpOnly;
        assert testCookie.getSameSite().equals(sameSite);
    }

    public void testGetCookie(String domain, String name) {
        Cookie cookie = getCookie(domain, name);
        assert cookie != null;
    }

    public void testDeleteCookie(String domain, String name) {
        deleteCookie(domain, name);
        Cookie deletedCookie = getCookie(domain, name);
        assert deletedCookie == null;
    }

    public void testListCookies() {
        Map<String, Map<String, Cookie>> allCookies = listCookies();
        assert !allCookies.isEmpty();
    }

    public void testClearCookies() {
        clearCookies();
        Map<String, Map<String, Cookie>> allCookies = listCookies();
        assert allCookies.isEmpty();

        setCookie("example.com", "test_cookie", "value123", Duration.ofHours(1), false, false, "Lax");
        setCookie("ads.example.com", "ad_id", "xyz456", Duration.ofDays(30), false, false, "None");
        clearCookies("example.com");
        allCookies = listCookies();
        assert allCookies.size() == 1;
        assert allCookies.containsKey("ads.example.com");
    }
}
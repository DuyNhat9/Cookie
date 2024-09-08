package com.example.cookie_demo;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CookieSystem {
    public static void main(String[] args) {
        // Tạo và sử dụng CookieManager
        CookieManager cookieManager = new CookieManager();

        // Thêm một số cookie
        addSecureCookies(cookieManager);

        // Lấy cookie
        displayCookie(cookieManager, "example.com", "username");

        // Xóa cookie
        deleteCookie(cookieManager, "example.com", "session_id");

        // Liệt kê các cookie
        listAllCookies(cookieManager);

        // Kiểm tra các tính năng
        testSetCookie(cookieManager);
        testGetCookie(cookieManager);
        testDeleteCookie(cookieManager);
        testListCookies(cookieManager);
        testClearCookies(cookieManager);
    }

    private static void addSecureCookies(CookieManager cookieManager) {
        cookieManager.setCookie("example.com", "username", "john_doe", Duration.ofHours(1), true, true, "Strict");
        cookieManager.setCookie("example.com", "session_id", "abc123", Duration.ofDays(1), true, true, "Strict");
        cookieManager.setCookie("ads.example.com", "ad_id", "xyz456", Duration.ofDays(30), false, false, "None");
    }

    private static void displayCookie(CookieManager cookieManager, String domain, String name) {
        Cookie cookie = cookieManager.getCookie(domain, name);
        if (cookie != null) {
            System.out.println("Cookie name: " + cookie.getName());
            System.out.println("Cookie value: " + cookie.getValue());
            System.out.println("Cookie domain: " + domain);
            System.out.println("Cookie path: /");
            System.out.println("Cookie expires: " + formatExpirationTime(cookie.getExpirationTime()));
            System.out.println("Cookie secure: " + cookie.isSecure());
            System.out.println("Cookie HttpOnly: " + cookie.isHttpOnly());
            System.out.println("Cookie SameSite: " + cookie.getSameSite());
        } else {
            System.out.println("Cookie not found.");
        }
    }

    private static void deleteCookie(CookieManager cookieManager, String domain, String name) {
        cookieManager.deleteCookie(domain, name);
        System.out.println("Cookie " + name + " deleted from domain " + domain + ".");
    }

    private static void listAllCookies(CookieManager cookieManager) {
        Map<String, Map<String, Cookie>> allCookies = cookieManager.listCookies();
        System.out.println("All cookies:");
        for (Map.Entry<String, Map<String, Cookie>> domainEntry : allCookies.entrySet()) {
            String domain = domainEntry.getKey();
            for (Map.Entry<String, Cookie> cookieEntry : domainEntry.getValue().entrySet()) {
                Cookie cookie = cookieEntry.getValue();
                System.out.println("Domain: " + domain + ", Cookie name: " + cookie.getName() + ", Cookie value: " + cookie.getValue());
            }
        }
    }

    private static String formatExpirationTime(Instant expirationTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return expirationTime.atZone(java.time.ZoneId.systemDefault()).format(formatter);
    }

    private static void testSetCookie(CookieManager cookieManager) {
        cookieManager.testSetCookie("example.com", "test_cookie", "value123", Duration.ofHours(1), true, true, "Strict");
    }

    private static void testGetCookie(CookieManager cookieManager) {
        cookieManager.testGetCookie("example.com", "username");
    }

    private static void testDeleteCookie(CookieManager cookieManager) {
        cookieManager.testDeleteCookie("example.com", "session_id");
    }

    private static void testListCookies(CookieManager cookieManager) {
        cookieManager.testListCookies();
    }

    private static void testClearCookies(CookieManager cookieManager) {
        cookieManager.testClearCookies();
    }
}
package com.javaweb.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CookieUtils {

    private final JwtTokenUtils jwtTokenUtils;

    /**
     * Lấy userId từ JWT token trong cookie.
     * @param request HttpServletRequest
     * @return userId nếu token hợp lệ, null nếu không tìm thấy hoặc token không hợp lệ.
     */
    public Long getUserId(HttpServletRequest request) {
        try {
            String token = getAccessToken(request);
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("Token is null or empty.");
            }
            return jwtTokenUtils.extractUserId(token);
        } catch (Exception e) {
            // Log lỗi nếu cần
            System.err.println("Failed to extract userId from token: " + e.getMessage());
            return null;
        }
    }

    public String getUsername(HttpServletRequest request) {
        try {
            String token = getAccessToken(request);
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("Token is null or empty.");
            }
            return jwtTokenUtils.extractUsername(token);

        } catch (Exception e) {
            // Log lỗi nếu cần
            System.err.println("Failed to extract userId from token: " + e.getMessage());
            return null;
        }
    }


    /**
     * Lấy JWT token từ cookie.
     * @param request HttpServletRequest
     * @return Token nếu tìm thấy, null nếu không tìm thấy.
     */
    public String getAccessToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            System.err.println("No cookies found in the request.");
            return null;
        }
        Optional<Cookie> jwtCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst();

        if (jwtCookie.isEmpty()) {
            System.err.println("JWT token not found in cookies.");
            return null;
        }

        String token = jwtCookie.get().getValue();
        if (token == null || token.trim().isEmpty()) {
            System.err.println("Token in cookie is null or empty.");
            return null;
        }

        return token;
    }

    public String getRefreshToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            System.err.println("No cookies found in the request.");
            return null;
        }
        Optional<Cookie> jwtCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst();

        if (jwtCookie.isEmpty()) {
            System.err.println("JWT token not found in cookies.");
            return null;
        }

        String token = jwtCookie.get().getValue();
        if (token == null || token.trim().isEmpty()) {
            System.err.println("Token in cookie is null or empty.");
            return null;
        }

        return token;
    }
}

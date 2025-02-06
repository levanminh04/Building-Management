package com.javaweb.filter;


import com.javaweb.entity.UserEntity;
import com.javaweb.utils.CookieUtils;
import com.javaweb.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor

public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    private final UserDetailsService userDetailsService;

    private final CookieUtils cookieUtils;


    public static final Set<String> WHITE_LIST = new HashSet<>();
    static {
        WHITE_LIST.add("/resources/**");
        WHITE_LIST.add("/login");
        WHITE_LIST.add("/trang-chu");
        WHITE_LIST.add("/logout");
        WHITE_LIST.add("/web/**");
        WHITE_LIST.add("/css/**");
        WHITE_LIST.add("/images/**");
        WHITE_LIST.add("/img/**");
        WHITE_LIST.add("/fonts/**");
        WHITE_LIST.add("/js/**");
        WHITE_LIST.add("/WEB-INF/**");
        WHITE_LIST.add("/lien-he");
        WHITE_LIST.add("/api/user/refresh-token");
        WHITE_LIST.add("/tin-tuc");
        WHITE_LIST.add("/api/customer/contact");
        WHITE_LIST.add("/san-pham");
        WHITE_LIST.add("/gioi-thieu");
        WHITE_LIST.add("/webapp/**");
        WHITE_LIST.add("/index.jsp");
        WHITE_LIST.add("/");
        WHITE_LIST.add("/sign-up");
        WHITE_LIST.add("/building/**");
        WHITE_LIST.add("/reset-password");
        WHITE_LIST.add("/api/auth/send-otp");
        WHITE_LIST.add("/api/auth/verify-otp");
        WHITE_LIST.add("/api/auth/reset-password");
        WHITE_LIST.add("/api/user/register");


    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String token = cookieUtils.getAccessToken(request);
            String refreshToken = cookieUtils.getRefreshToken(request);

            if (shouldNotFilter(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            if (token == null && refreshToken == null) {
                logger.warn("Access denied: Both tokens are missing");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendRedirect("/login");
                return;
            }

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            final String userName = jwtTokenUtils.extractUsername(token);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails  userEntity = userDetailsService.loadUserByUsername(userName);
                System.out.println("@#  " + userEntity);
                if (jwtTokenUtils.isValidateToken(token, userEntity)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userEntity,
                            null,
                            userEntity.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);



                }
            }


            filterChain.doFilter(request, response);


        }catch(Exception e){
            e.printStackTrace();
        }


    }

@Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    AntPathMatcher pathMatcher = new AntPathMatcher();
    return WHITE_LIST.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
}




}

package com.javaweb.config;

import com.javaweb.filter.JwtTokenFilter;
import com.javaweb.security.CustomSuccessHandler;
import com.javaweb.service.impl.CustomUserDetailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Nếu token không hợp lệ hoặc không có, và bạn đã gửi lỗi 401 trong jwtTokenFilter, UsernamePasswordAuthenticationFilter sẽ không bao giờ được gọ

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Explicitly disable session creation ,jwt là stateless vì vậy nếu không sử dụng đến session thì thêm lệnh này vào để ngăn spring security tự động tạo JSESSIONID
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class) // thêm một bộ lọc tùy chỉnh (custom filter) vào chuỗi filter chain của Spring Security, đảm bảo rằng jwtTokenFilter sẽ được thực thi trước bộ lọc UsernamePasswordAuthenticationFilter. UsernamePasswordAuthenticationFilter là một bộ lọc mặc định của Spring Security, dùng để xử lý xác thực dựa trên tên người dùng và mật khẩu
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/admin/**").hasAnyRole("MANAGER", "STAFF", "ADMIN")
                            .requestMatchers(
                                    "/login",
                                    "/resources/**",
                                    "/web/**",
                                    "/trang-chu",
                                    "/api/user/refresh-token",
                                    "/api/customer/contact",
                                    "/lien-he",
                                    "/tin-tuc",
                                    "/san-pham",
                                    "/gioi-thieu",
                                    "/index.jsp",
                                    "/WEB-INF/**",
                                    "/",
                                    "/logout", // Spring Security mặc định xử lý các yêu cầu POST đến /logout theo cơ chế của LogoutFilter, vì vậy nếu muốn cấu hình /logout theo cách riêng thì cần phải lưu ý, LogoutFilter thực hiện chuyển hướng (Redirecting to /login?logout) đến trang đăng nhập với tham số logout
                                    "/sign-up",
                                    "/reset-password",
                                    "/building/**",
                                    "/css/**",
                                    "/images/**",
                                    "/img/**",
                                    "/js/**",
                                    "/fonts/**",
                                    "/api/auth/send-otp",
                                    "/api/auth/verify-otp",
                                    "/api/auth/reset-password",
                                    "/api/user/register"
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                .headers(headers -> headers
                        .cacheControl(cache -> cache.disable()) // ✅ Cho phép cache
                )
                .logout(logout -> {
                    logout
                            .logoutUrl("/logout") // Endpoint xử lý yêu cầu đăng xuất
                            .logoutSuccessHandler((request, response, authentication) -> {
                                // Xử lý khi đăng xuất thành công
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write("{\"message\": \"Logout successful!\"}");
                                response.getWriter().flush();
                            })
                            .deleteCookies("token", "refreshToken"); // Xóa cookie chứa JWT (nếu cần) "phải đặt đúng tên key = token do mình tự cấu hình trước đó nếu không nó sẽ không xóa đúng token đâu
                })

                .authenticationProvider(authenticationProvider);
            return http.build();
    }


}

package com.javaweb.config;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.impl.CustomUserDetailService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
//        return username -> {
//            UserEntity user = userRepository.findOneByUserNameAndStatus(username, 1)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            if (!(user instanceof UserDetails)) {
//                throw new IllegalStateException("User is not an instance of UserDetails");
//            }
//            System.out.println("User is an instance of UserDetails");
//            return user;
//        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    // AuthenticationManager Gọi đến các AuthenticationProvider đã đăng ký để xác thực thông tin người dùng
    // AuthenticationManager  gọi một AuthenticationProvider để xử lý logic cụ thể
    // và AuthenticationProvider  sử dụng UserDetailsService để tải thông tin người dùng, vì vậy cẩn thận nếu không sẽ bị lỗi vòng lặp phụ thuộc (circular dependency),
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
    // DaoAuthenticationProvider) để xử lý xác thực từ username/password.
}

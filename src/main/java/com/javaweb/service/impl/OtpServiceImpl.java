package com.javaweb.service.impl;

import com.javaweb.repository.UserRepository;
import com.javaweb.service.OtpService;
import com.javaweb.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final RedisTemplate<String, String> redisTemplate;
    private final JavaMailSender mailSender;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRepository userRepository;

    private static final String OTP_PREFIX = "OTP_";
    private static final long OTP_EXPIRATION_MINUTES = 5;

    @Override
    public void sendOtp(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email không tồn tại trong hệ thống.");
        }

        // Tạo OTP ngẫu nhiên 6 chữ số
        String otp = String.format("%06d", new Random().nextInt(999999));
        String x = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(OTP_PREFIX + email, otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        // key = OTP_PREFIX + email       value = mã otp 6 chữ số       OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES:  TTL = 5 - đơn vị phút
        sendEmail(email, "Mã OTP của bạn", "Mã OTP của bạn là: " + otp);
    }

    @Override
    public boolean verifyOtp(String email, String otpCode) {
        String storedOtp = redisTemplate.opsForValue().get(OTP_PREFIX + email);
        if (storedOtp != null && storedOtp.equals(otpCode)) {
            redisTemplate.delete(OTP_PREFIX + email); // Xóa OTP sau khi xác thực
            return true;
        }
        return false;
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public String generateResetToken(String email) throws Exception {
        return jwtTokenUtils.generateResetToken(email, Duration.ofMinutes(10)); // Token tạm thời 10 phút
    }
}


package com.javaweb.service;

public interface OtpService {
    void sendOtp(String email);
    boolean verifyOtp(String email, String otpCode);
    void sendEmail(String to, String subject, String content);
    String generateResetToken(String email) throws Exception;
}

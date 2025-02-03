package com.javaweb.api.admin;

import com.javaweb.model.dto.OtpRequestDTO;
import com.javaweb.model.dto.OtpVerificationRequestDTO;
import com.javaweb.model.dto.ResetPasswordRequestDTO;
import com.javaweb.service.IUserService;
import com.javaweb.service.OtpService;
import com.javaweb.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OtpAPI {

    private final OtpService otpService;
    private final JwtTokenUtils jwtTokenUtils;
    private final IUserService userService;

    // 1️⃣ Gửi OTP qua email
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpRequestDTO request) {
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("message", "OTP đã được gửi."));
    }

    // 2️⃣ Xác thực OTP và tạo Reset Token
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationRequestDTO request) throws Exception {
        if (otpService.verifyOtp(request.getEmail(), request.getOtpCode())) {
            String resetToken = otpService.generateResetToken(request.getEmail());
            return ResponseEntity.ok(Collections.singletonMap("resetToken", resetToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "OTP không hợp lệ."));
    }

    // 3️⃣ Đặt lại mật khẩu với Reset Token
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        if (!jwtTokenUtils.isValidateResetToken(request.getResetToken(), request.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Reset Token không hợp lệ."));
        }

        userService.resetPassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok(Collections.singletonMap("message", "Mật khẩu đã được cập nhật."));
    }
}

package com.javaweb.model.dto;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String email;
    private String resetToken;
    private String newPassword;
}
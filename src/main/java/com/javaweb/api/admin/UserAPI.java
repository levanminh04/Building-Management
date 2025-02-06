package com.javaweb.api.admin;

import com.cloudinary.api.ApiResponse;
import com.javaweb.constant.SystemConstant;
import com.javaweb.customException.DuplicateUserException;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import com.javaweb.utils.CookieUtils;
import com.javaweb.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import com.javaweb.entity.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserAPI {


    private final IUserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final CookieUtils cookieUtils;
    private final UserRepository UserRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (MyException e) {
            //LOGGER.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
        if (idList.length > 0) {
            userService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        try {
            String refreshToken = cookieUtils.getRefreshToken(request);

            String username = jwtTokenUtils.extractUsername(refreshToken);
            Optional<UserEntity> user = userRepository.findOneByUserNameAndStatus(username, 1);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!jwtTokenUtils.isValidateToken(refreshToken, userDetails)) {
                throw new RuntimeException("Invalid or expired refresh token");
            }

            String newAccessToken = jwtTokenUtils.generateToken(user.get());
            String newRefreshToken = jwtTokenUtils.generateRefreshToken(user.get());

            ResponseCookie newAccessTokenCookie = ResponseCookie.from("token", newAccessToken)
                    .httpOnly(true)
                    .secure(true)
                    .maxAge(Duration.ofMinutes(60))
                    .path("/")
                    .build();

            ResponseCookie newRefreshTokenCookie = ResponseCookie.from("refreshToken", newRefreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .maxAge(Duration.ofDays(7))
                    .path("/")  // không gửi kèm refresh Token trong mọi request
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, newAccessTokenCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, newRefreshTokenCookie.toString())
                    .body(Map.of("message", "Token refreshed"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO newUser) {
        try {
            userService.createUser(newUser);
            return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
        } catch (DuplicateUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while processing the request."));
        }
    }
}

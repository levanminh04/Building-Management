package com.javaweb.utils;



import com.javaweb.customException.InvalidParamException;
import com.javaweb.entity.UserEntity;
import com.javaweb.filter.JwtTokenFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtils {

    public String generateToken(UserEntity userEntity) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userEntity.getUsername());
        claims.put("id", userEntity.getId());
        claims.put("jti", UUID.randomUUID().toString());
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userEntity.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }

    public String generateRefreshToken(UserEntity userEntity) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userEntity.getUsername());
        claims.put("id", userEntity.getId());
        claims.put("jti", UUID.randomUUID().toString());
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userEntity.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }

    public String generateResetToken(String email, Duration expirationTime) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(email)
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + expirationTime.toMillis()))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

        } catch (Exception e) {
            throw new InvalidParamException(e.getMessage());
        }
    }


    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode("TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI=");

        return Keys.hmacShaKeyFor(bytes);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = this.extractClaims(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return extractClaims(token, claims -> ((Number) claims.get("id")).longValue());
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Kiểm tra xem token có hợp lệ không
    public boolean isValidateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isValidateResetToken(String token, String email) {
        String mail = extractEmail(token);
        return mail.equals(email) && !isTokenExpired(token);
    }

    public boolean isTokenUserNameValid(String token, String userName) {
        String usernameToken = extractUsername(token);
        return usernameToken.equals(userName);
    }
}

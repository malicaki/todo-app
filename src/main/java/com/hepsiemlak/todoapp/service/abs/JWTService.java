package com.hepsiemlak.todoapp.service.abs;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}

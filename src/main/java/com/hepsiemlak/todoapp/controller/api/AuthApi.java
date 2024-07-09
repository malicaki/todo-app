package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.JWTAuthenticationResponse;
import com.hepsiemlak.todoapp.dto.LoginDto;
import com.hepsiemlak.todoapp.dto.RefreshTokenDto;
import com.hepsiemlak.todoapp.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface AuthApi {

    @Operation(summary = "Login user", description = "Sign in to app")
    @PostMapping(value = APP_ROOT + "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JWTAuthenticationResponse> loginUser(@RequestBody LoginDto loginDto);


    @Operation(summary = "Register user", description = "Creates a new user")
    @PostMapping(value = APP_ROOT + "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> registerUser(@RequestBody UserDto user);


    @Operation(summary = "Refresh JWT Token", description = "Refresh token for a week")
    @PostMapping(value = APP_ROOT + "/auth/refresh", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto);
}

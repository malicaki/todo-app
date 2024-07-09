package com.hepsiemlak.todoapp.controller;


import com.hepsiemlak.todoapp.controller.api.AuthApi;
import com.hepsiemlak.todoapp.dto.JWTAuthenticationResponse;
import com.hepsiemlak.todoapp.dto.LoginDto;
import com.hepsiemlak.todoapp.dto.RefreshTokenDto;
import com.hepsiemlak.todoapp.dto.UserDto;
import com.hepsiemlak.todoapp.service.abs.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthController implements AuthApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<JWTAuthenticationResponse> loginUser(LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> registerUser(UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JWTAuthenticationResponse> refreshToken(RefreshTokenDto refreshTokenDto) {
        return new ResponseEntity<>(userService.refreshToken(refreshTokenDto), HttpStatus.OK);
    }
}
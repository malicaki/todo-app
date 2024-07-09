package com.hepsiemlak.todoapp.service.abs;

import com.hepsiemlak.todoapp.dto.JWTAuthenticationResponse;
import com.hepsiemlak.todoapp.dto.LoginDto;
import com.hepsiemlak.todoapp.dto.RefreshTokenDto;
import com.hepsiemlak.todoapp.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    List<UserDto> findAll();

    UserDto findById(Long id);

    void delete(Long id);

    JWTAuthenticationResponse login(LoginDto user);

    JWTAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto);
}

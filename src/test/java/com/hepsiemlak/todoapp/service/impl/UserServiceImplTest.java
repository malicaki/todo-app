package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.JWTAuthenticationResponse;
import com.hepsiemlak.todoapp.dto.LoginDto;
import com.hepsiemlak.todoapp.dto.RefreshTokenDto;
import com.hepsiemlak.todoapp.dto.UserDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.User;
import com.hepsiemlak.todoapp.repository.UserRepository;
import com.hepsiemlak.todoapp.service.abs.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_validUser_shouldReturnSavedUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setUserName("test1");
        userDto.setEmail("test1@example.com");
        userDto.setPassword("passwordtest1");

        User user = UserDto.toEntity(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        UserDto savedUser = userService.save(userDto);

        assertNotNull(savedUser);
        assertEquals("test", savedUser.getFirstName());
        assertEquals("test", savedUser.getLastName());
        assertEquals("test1", savedUser.getUserName());
    }

    @Test
    void save_invalidUser_shouldThrowInvalidEntityException() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("");
        userDto.setUserName("");
        userDto.setEmail("");
        userDto.setPassword("");

        assertThrows(InvalidEntityException.class, () -> userService.save(userDto));
    }

    @Test
    void findById_validId_shouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("test");
        user.setLastName("test");
        user.setUserName("test1");
        user.setEmail("test1@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto userDto = userService.findById(1L);

        assertNotNull(userDto);
        //assertEquals(1L, userDto.getId());
        assertEquals("test", userDto.getFirstName());
    }

    @Test
    void findById_invalidId_shouldThrowEntityNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    void login_validCredentials_shouldReturnJWTAuthenticationResponse() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("test1");
        loginDto.setPassword("passwordtest");

        User user = new User();
        user.setUserName("test1");
        user.setPassword("encodedPassword");

        when(userRepository.findUserByUserName("test1")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("jwt-token");
        when(jwtService.generateRefreshToken(any(), any(UserDetails.class))).thenReturn("refresh-token");

        JWTAuthenticationResponse response = userService.login(loginDto);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
        assertEquals("refresh-token", response.getRefreshToken());
    }

    @Test
    void refreshToken_validToken_shouldReturnJWTAuthenticationResponse() {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setRefreshToken("valid-refresh-token");

        User user = new User();
        user.setUserName("test1");

        when(jwtService.extractUserName("valid-refresh-token")).thenReturn("test1");
        when(userRepository.findUserByUserName("test1")).thenReturn(Optional.of(user));
        when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("new-jwt-token");

        JWTAuthenticationResponse response = userService.refreshToken(refreshTokenDto);

        assertNotNull(response);
        assertEquals("new-jwt-token", response.getToken());
        assertEquals("valid-refresh-token", response.getRefreshToken());
    }
}

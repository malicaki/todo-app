package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.JWTAuthenticationResponse;
import com.hepsiemlak.todoapp.dto.LoginDto;
import com.hepsiemlak.todoapp.dto.RefreshTokenDto;
import com.hepsiemlak.todoapp.dto.UserDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.ErrorCodes;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.User;
import com.hepsiemlak.todoapp.repository.UserRepository;
import com.hepsiemlak.todoapp.service.abs.JWTService;
import com.hepsiemlak.todoapp.service.abs.UserService;
import com.hepsiemlak.todoapp.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public UserDto save(UserDto user) {
        List<String> errors = UserValidator.validateUser(user);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", user);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }
        User userEntity = UserDto.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return UserDto.fromEntity(userRepository.save(userEntity));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            log.error("User id is null");
            return null;
        }
        return userRepository.findById(id).map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("No user found with ID = " + id, ErrorCodes.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public JWTAuthenticationResponse login(LoginDto user) {
        List<String> errors = UserValidator.validateUserCredentials(user.getUsername(), user.getPassword());
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        User foundUser = userRepository.findUserByUserName(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("No user found with name = " + user.getUsername(), ErrorCodes.USER_NOT_FOUND));
        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new EntityNotFoundException("Invalid password for user = " + user.getUsername(), ErrorCodes.USER_NOT_FOUND);
        }

        String jwt = jwtService.generateToken(foundUser);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), foundUser);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    @Override
    public JWTAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        String username = jwtService.extractUserName(refreshTokenDto.getRefreshToken());

        User foundUser = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("No user found with username = " + username, ErrorCodes.USER_NOT_FOUND));


        if (jwtService.isTokenValid(refreshTokenDto.getRefreshToken(), foundUser)) {
            String jwt = jwtService.generateToken(foundUser);

            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenDto.getRefreshToken());

            return jwtAuthenticationResponse;


        }

        return null;
    }
}

package com.hepsiemlak.todoapp.service.impl;

import com.hepsiemlak.todoapp.dto.UserDto;
import com.hepsiemlak.todoapp.exception.EntityNotFoundException;
import com.hepsiemlak.todoapp.exception.ErrorCodes;
import com.hepsiemlak.todoapp.exception.InvalidEntityException;
import com.hepsiemlak.todoapp.model.User;
import com.hepsiemlak.todoapp.repository.UserRepository;
import com.hepsiemlak.todoapp.service.abs.UserService;
import com.hepsiemlak.todoapp.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public UserDto login(UserDto user) {
        List<String> errors = UserValidator.validateUserCredentials(user.getEmail(), user.getPassword());
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }
        User foundUser = userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("No user found with Email = " + user.getEmail(), ErrorCodes.USER_NOT_FOUND));
        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new EntityNotFoundException("Invalid password for Email = " + user.getEmail(), ErrorCodes.USER_NOT_FOUND);
        }
        return UserDto.fromEntity(foundUser);
    }
}

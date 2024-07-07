package com.hepsiemlak.todoapp.controller;


import com.hepsiemlak.todoapp.controller.api.AuthApi;
import com.hepsiemlak.todoapp.dto.UserDto;
import com.hepsiemlak.todoapp.service.abs.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController implements AuthApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDto> loginUser(UserDto user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }
}
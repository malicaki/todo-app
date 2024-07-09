package com.hepsiemlak.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthenticationResponse {

    private String token;

    private String refreshToken;


}

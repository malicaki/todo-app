package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface AuthApi {

    @Operation(summary = "Login user", description = "Creates a new user", responses = {
            @ApiResponse(responseCode = "201", description = "The connected user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @PostMapping(value = APP_ROOT + "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> loginUser(
            @RequestBody UserDto user
    );
}

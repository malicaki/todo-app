package com.hepsiemlak.todoapp.controller.api;

import com.hepsiemlak.todoapp.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hepsiemlak.todoapp.util.Constants.APP_ROOT;

public interface UserApi {

    @Operation(summary = "Create user", description = "Creates a new user", responses = {
            @ApiResponse(responseCode = "201", description = "The newly created user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @PostMapping(value = APP_ROOT + "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> createUser(
            @RequestBody UserDto user);

    @Operation(summary = "Update user", description = "Updates an existing user", responses = {
            @ApiResponse(responseCode = "201", description = "The updated user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @PatchMapping(value = APP_ROOT + "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto user);

    @Operation(summary = "User Details", description = "Returns the list of the users", responses = {
            @ApiResponse(responseCode = "200", description = "List of the users", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(summary = "User Details", description = "Returns the user by ID", responses = {
            @ApiResponse(responseCode = "200", description = "The user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping(value = APP_ROOT + "/users/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(
            @PathVariable("id") Long id
    );

    @Operation(summary = "Delete a user", description = "Deletes a user by ID", responses = {
            @ApiResponse(responseCode = "200", description = "The user deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(value = APP_ROOT + "/users/delete/{id:.+}")
    ResponseEntity<Void> deleteUser(
            @PathVariable("id") Long id
    );
}

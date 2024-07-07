package com.hepsiemlak.todoapp.handler;

import com.hepsiemlak.todoapp.exception.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Holds error code, error message and related error messages of an error")
public class ErrorDto {

    @Schema(description = "The HTTP status code.", required = true, example = "400")
    private Integer httpCode;

    @Schema(description = "The error code.", required = true)
    private ErrorCodes code;

    @Schema(description = "A detailed error message.", example = "Invalid input data")
    private String message;

    @Schema(description = "The input fields related to the error, if any.", example = "[\"field1\", \"field2\"]")
    List<String> errors = new ArrayList<>();
}

package org.skyhawk.nbastats.controller;

import jakarta.persistence.OptimisticLockException;
import org.skyhawk.nbastats.exception.PlayerStatsCreationForNotExistingPlayerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return switch (ex) {
            case PlayerStatsCreationForNotExistingPlayerException notExistingPlayer ->
                    ResponseEntity.internalServerError()
                            .body(ErrorResponse.builder(notExistingPlayer, HttpStatus.INTERNAL_SERVER_ERROR,
                                    "One or more playerIds specified in the request payload don't exist").build());
            case OptimisticLockException optimisticLockException -> ResponseEntity.internalServerError()
                    .body(ErrorResponse.builder(optimisticLockException, HttpStatus.INTERNAL_SERVER_ERROR,
                            "Concurrent access to the shared entity detected, please try again").build());
            default -> ResponseEntity.internalServerError()
                    .body(ErrorResponse.builder(ex, HttpStatus.INTERNAL_SERVER_ERROR,
                            "Internal Server error, for more details contact our operations team").build());
        };
    }
}

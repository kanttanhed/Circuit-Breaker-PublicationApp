package com.kanttanhed.Publication.handler;

import com.kanttanhed.Publication.domain.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    // ExceptionHandler annotation to specify that this method handles exceptions of type Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerMethodException(
            Exception exception,
            HttpServletRequest Request
    ){
        // Creating an ErrorResponse object with details of the exception
        ErrorResponse error =  ErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .path(Request.getRequestURI())
                .build();

        // Logging the error with the error details and the exception
            //log.error("[ERROR] payload={}", error, exception);
        // Returning a ResponseEntity with the error information and an HTTP status of 500
        return ResponseEntity.internalServerError().body(error);
    }
}

package com.gautam.crudSpringBootDemo.exception;


import com.gautam.crudSpringBootDemo.dto.ExceptionResponseDTO;
import com.gautam.crudSpringBootDemo.dto.ValidationExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRunTimeException(
            RuntimeException ex,
            HttpServletRequest httpServletRequest // because it stores your path
    ) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                Instant.now(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                httpServletRequest.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDTO);
    }


    // this is for fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(
            Exception ex,
            HttpServletRequest httpServletRequest
    ) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                Instant.now(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest httpServletRequest
    ) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                Instant.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseDTO);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDuplicateResourceException(
            DuplicateResourceException ex,
            HttpServletRequest httpServletRequest
    ) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                HttpStatus.CONFLICT.getReasonPhrase(),
                Instant.now(),
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseDTO);
    }

    // MethodArgumentNotValidException.class is used for validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest httpServletRequest
    ) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(
                                error.getField(),
                                error.getDefaultMessage()));


        ValidationExceptionResponseDTO validationExceptionResponseDTO = new ValidationExceptionResponseDTO(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                httpServletRequest.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validationExceptionResponseDTO);
    }
}

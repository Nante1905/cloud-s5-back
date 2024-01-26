package com.cloud.voiture.controllers.handlers;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.cloud.voiture.exceptions.ValidationException;
import com.cloud.voiture.services.utilities.Utilities;
import com.cloud.voiture.types.response.Response;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.security.auth.message.AuthException;

@RestControllerAdvice
public class ValidationExceptionHandler {
    private static HashMap<String, String> ERROR_MESSAGE = new HashMap<String, String>() {
        {
            put("NotBlank", "%field% ne doit pas être vide.");
            put("NotNull", "%field% est obligatoire.");
            put("Min", "%field% doit être strictement supérieure à %value%");
            put("Max", "%field% ne doit pas dépasser %value%");
            put("UniqueElements", "%field% doit être unique.");

        };
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationError(MethodArgumentNotValidException exception) {
        String errorMessage = "";
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError : errors) {
            System.out.println(
                    fieldError.getDefaultMessage().equals("") + fieldError.getCode() + " " + fieldError.getField());
            if (fieldError.getDefaultMessage() != null && !fieldError.getDefaultMessage().equals("")) {
                System.out.println("miditra ato " + fieldError.getField());
                errorMessage += fieldError.getDefaultMessage() + ".";
            } else {
                errorMessage += ERROR_MESSAGE.get(fieldError.getCodes()[fieldError.getCodes().length - 1])
                        .replaceAll("%field%", Utilities.capitalize(fieldError.getField()));
                if (fieldError.getRejectedValue() != null
                        && ERROR_MESSAGE.get(fieldError.getCode()).contains("%value%")) {
                    errorMessage = errorMessage.replaceAll("%value%", fieldError.getRejectedValue().toString());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(errorMessage));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleUnprosseasableMsgException(HttpMessageNotReadableException exception)
            throws ValidationException {
        System.out.println(exception.getCause());
        if (exception.getCause() instanceof JsonMappingException) {
            JsonMappingException jsonEx = (JsonMappingException) exception.getCause();
            if (jsonEx.getCause() instanceof ValidationException) {
                ValidationException validationEx = (ValidationException) jsonEx.getCause();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response(validationEx.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response("Type de donnée invalide pour " + jsonEx.getPath().get(0).getFieldName()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response("Type de donnée invalide. "));
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Response> handleCustomValidationException(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response(exception.getMessage()));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<Response> handleCustomNoResourceFoundException(NoResourceFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response("Cette ressource n'existe pas."));
}
    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<Response> handleAuthException(AuthException exception) {
        System.out.println("ATOOOOOOOOOOOOOOOO");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Response(exception.getMessage()));
    }

}

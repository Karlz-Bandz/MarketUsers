package com.izzisoft.users.exception;

import com.izzisoft.users.dto.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ErrResponse> handleWrongPasswordException(WrongCredentialsException ex) {
        return new ResponseEntity<>(new ErrResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailNotExistsException.class)
    public ResponseEntity<ErrResponse> handleEmailNotExistsException(EmailNotExistsException ex) {
        return new ResponseEntity<>(new ErrResponse(ex.getMessage()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }
}

package com.example.websocket_chat.global.advice;

import com.example.websocket_chat.global.error.ErrorCode;
import com.example.websocket_chat.global.exception.*;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import static com.example.websocket_chat.global.error.ErrorStaticField.*;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ErrorCode> bindingExceptionHandler(TypeMismatchException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCode);
    }


    @ExceptionHandler(BindingException.class)
    public ResponseEntity<ErrorCode> bindingExceptionHandler(BindingException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(BAD_REQUEST)
                .message(BINDING_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCode);
    }

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ErrorCode> duplicationLoginIdExceptionHandler(DuplicationException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCode);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorCode> forbiddenExceptionHandler(ForbiddenException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(FORBIDDEN)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorCode);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorCode> notFoundExceptionHandler(NotFoundException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCode);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorCode> unauthorizedExceptionHandler(UnauthorizedException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorCode);
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<ErrorCode> unauthorizedExceptionHandler(NotMatchException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(NOT_MATCH)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(NOT_MATCH).body(errorCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCode> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorCode);
    }

    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public void methodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException ex) {
        throw new MethodArgumentNotConversionException();
    }

    @ExceptionHandler(MethodArgumentNotConversionException.class)
    public ResponseEntity<ErrorCode> methodArgumentNotConversionException(MethodArgumentNotConversionException ex) {
        ErrorCode errorCode = ErrorCode.builder()
                .errorCode(BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorCode);
    }
}

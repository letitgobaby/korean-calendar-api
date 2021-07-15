package com.calendar.letitgobaby.exception;

import java.security.InvalidParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    ErrorResponse response = ErrorResponse.create()
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .message(e.getMessage());

    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
  }

  
  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
    ErrorResponse response = ErrorResponse.create()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.toString());

    return new ResponseEntity<>(response, HttpStatus.resolve(HttpStatus.BAD_REQUEST.value()));
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> validException(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    ErrorResponse response = ErrorResponse.create()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(bindingResult.getFieldError().getDefaultMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }


  // @ExceptionHandler(Exception.class)
  // protected ResponseEntity<ErrorResponse> handleException(Exception e) {
  //   ErrorResponse response = ErrorResponse.create()
  //       .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
  //       .message(e.toString());

  //   return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  // }
}

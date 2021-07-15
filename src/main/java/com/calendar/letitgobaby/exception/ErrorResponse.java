package com.calendar.letitgobaby.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

  private LocalDateTime timestamp = LocalDateTime.now();

  private String message;

  private int status;

  public ErrorResponse() {}

  static public ErrorResponse create() {
    return new ErrorResponse();
  }

  public ErrorResponse status(int status) {
    this.status = status;
    return this;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

}

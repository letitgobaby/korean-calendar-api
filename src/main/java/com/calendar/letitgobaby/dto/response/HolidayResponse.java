package com.calendar.letitgobaby.dto.response;

import java.util.ArrayList;

import lombok.*;

@Getter
@Setter
@ToString
public class HolidayResponse {
  
  private int status;
  private String message; 
  private ArrayList body;

  @Builder
  public HolidayResponse(ArrayList result) {
    if (!result.isEmpty()) {
      this.body = result;
      this.status = 200;
      this.message = "Success";
    }
  }
}

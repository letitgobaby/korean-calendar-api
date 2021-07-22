package com.calendar.letitgobaby.vo.payload;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarPayload {
  
  private int status;
  private String message; 
  private ArrayList result;

  @Builder
  public CalendarPayload(ArrayList result) {
    if (!result.isEmpty()) {
      this.result = result;
      this.status = 200;
      this.message = "Success";
    }
  }
}

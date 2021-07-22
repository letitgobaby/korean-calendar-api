package com.calendar.letitgobaby.vo.payload;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HolidayPayload {

  private int status;
  private String message; 
  private ArrayList result;

  @Builder
  public HolidayPayload(ArrayList result) {
    if (!result.isEmpty()) {
      this.result = result;
      this.status = 200;
      this.message = "Success";
    }
  }

}

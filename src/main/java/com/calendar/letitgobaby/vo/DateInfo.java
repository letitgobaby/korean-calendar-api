package com.calendar.letitgobaby.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DateInfo {

  private Solar solar;
  private Lunar lunar;
  private String isHoliday;
  private String dayOfWeek;
  
  public DateInfo() {}

  public DateInfo(Solar solar, Lunar lunar, String isHoliday) {
    this.solar = solar;
    this.lunar = lunar;
    this.isHoliday = isHoliday;
  }

}

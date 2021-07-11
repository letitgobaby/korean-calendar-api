package com.calendar.letitgobaby.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Lunar {

  private boolean isleap;
  private int lunarDay;
  private int lunarMonth;
  private int lunarYear;
  private String fulldate;
  
  public Lunar() { }

  public Lunar(int year, int month, int day, boolean isleap) {
    this.lunarYear = year;
    this.lunarMonth = month;
    this.lunarDay = day;
    this.isleap = isleap;
  }

}

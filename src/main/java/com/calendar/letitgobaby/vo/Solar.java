package com.calendar.letitgobaby.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Solar {

  private int solarDay;
  private int solarMonth;
  private int solarYear;
  private String fulldate;

  public Solar() { }

  public Solar(int year, int month, int day) {
    this.solarYear = year;
    this.solarMonth = month;
    this.solarDay = day;
  }
  
}

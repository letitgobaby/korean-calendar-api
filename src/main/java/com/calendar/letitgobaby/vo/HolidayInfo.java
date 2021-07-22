package com.calendar.letitgobaby.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HolidayInfo {
    
  private int year;
  private int month;
  private int date;
  private String fullDate;
  private String dateName;

  @Builder
  public HolidayInfo(int year, int month, int date, String dateName) {
    this.year = year;
    this.month = month;
    this.date = date;
    this.dateName = dateName;
    this.fullDate = dateToString(year, month, date);
  }

  private String dateToString(int year, int month, int date) {
		String result = "";
		result += Integer.toString(year);
		result += month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
		result += date < 10 ? "0" + Integer.toString(date) : Integer.toString(date);
		return result;
	}
}

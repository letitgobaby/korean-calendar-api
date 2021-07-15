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

  // public Solar getSolar() {
  //   return this.solar;
  // }

  // public Lunar getLunar() {
  //   return this.lunar;
  // }

  // public String getIsHoliday() {
  //   return this.isHoliday;
  // }

  // public String getDayOfWeek() {
  //   return this.dayOfWeek;
  // }

  // public void setSolar(Solar solar) {
  //   this.solar = solar;
  // }

  // public void setLunar(Lunar lunar) {
  //   this.lunar = lunar;
  // }

  // public void setIsHoliday(String isHoliday) {
  //   this.isHoliday = isHoliday;
  // }

  // public void setDayOfWeek(String dayOfWeek) {
  //   this.dayOfWeek = dayOfWeek;
  // }
}

package com.calendar.letitgobaby.util;

import java.util.Calendar;

import com.calendar.letitgobaby.vo.HolidayInfo;

import org.springframework.stereotype.Component;

@Component
public class DayCalculation {
  

  public HolidayInfo getPrevDate(HolidayInfo info) {
    Calendar cal = Calendar.getInstance();
    int year = info.getYear();
    int month = info.getMonth();
    int date = info.getDate();

    if (date == 1) {
      cal.set(info.getYear(), info.getMonth() - 1, info.getDate()); 
      year = month == 1 ? year - 1: year;
      month = month == 1 ? 12 : month - 1;

      cal.set(year, month, date);
      date = cal.getActualMaximum(Calendar.DATE);

      return new HolidayInfo().builder().year(year).month(month).date(date)
        .dateName(info.getDateName()).build();
    }

    return new HolidayInfo().builder()
      .year(info.getYear()).month(info.getMonth()).date(info.getDate() - 1)
      .dateName(info.getDateName()).build();
  }


  public HolidayInfo getNextDate(HolidayInfo info) {
    Calendar cal = Calendar.getInstance();
    int year = info.getYear();
    int month = info.getMonth();
    int date = info.getDate();

    cal.set(year, month - 1, date); 
    int thisMonthLastDate = cal.getActualMaximum(Calendar.DATE);

    if (info.getDate() == thisMonthLastDate) {
      year = month == 12 ? year + 1 : year;
      month = month == 12 ? 1 : month + 1;
      date = 1;
      return new HolidayInfo().builder().year(year).month(month).date(date)
        .dateName(info.getDateName()).build();
    }

    return new HolidayInfo().builder().year(year).month(month).date(date + 1)
      .dateName(info.getDateName()).build();
  }

}

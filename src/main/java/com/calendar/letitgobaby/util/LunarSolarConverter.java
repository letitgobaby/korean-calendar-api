package com.calendar.letitgobaby.util;

import java.util.Calendar;

import com.calendar.letitgobaby.vo.Lunar;
import com.calendar.letitgobaby.vo.Solar;
import com.ibm.icu.util.ChineseCalendar;

import org.springframework.stereotype.Component;


@Component
public class LunarSolarConverter {

	public Solar lunarToSolar(int year, int month, int date) {
    ChineseCalendar chinaCal = new ChineseCalendar();
		Calendar cal = Calendar.getInstance() ;

    chinaCal.set(ChineseCalendar.EXTENDED_YEAR, year + 2637);
    chinaCal.set(ChineseCalendar.MONTH, month - 1);
    chinaCal.set(ChineseCalendar.DAY_OF_MONTH, date);

		cal.setTimeInMillis(chinaCal.getTimeInMillis());

    return new Solar(
      cal.get(Calendar.YEAR), 
      cal.get(Calendar.MONTH) + 1,
      cal.get(Calendar.DATE)
    );
  }

	public Lunar lunarInfo(int year, int month, int date) {
		ChineseCalendar chinaCal = new ChineseCalendar();
		Calendar cal = Calendar.getInstance() ;
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, date);
		chinaCal.setTimeInMillis(cal.getTimeInMillis());
		 
		int lyear = chinaCal.get(ChineseCalendar.EXTENDED_YEAR) - 2637 ;
		int lmonth = chinaCal.get(ChineseCalendar.MONTH) + 1;
		int lday = chinaCal.get(ChineseCalendar.DAY_OF_MONTH);
		int leap = chinaCal.get(ChineseCalendar.IS_LEAP_MONTH);

		Lunar lunar = new Lunar(lyear, lmonth, lday, leap == 0 ? false : true);
		lunar.setFulldate(
			dateToString(lunar.getLunarYear(), lunar.getLunarMonth(), lunar.getLunarDay())
		);

		return lunar;
	}

	public Solar solarInfo(int year, int month, int date) {
		Solar solar = new Solar(year, month, date);
		solar.setFulldate(dateToString(year, month, date));

		return solar;
	}

	public String dateToString(int year, int month, int date) {
		String result = "";
		result += Integer.toString(year);
		result += month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
		result += date < 10 ? "0" + Integer.toString(date) : Integer.toString(date);

		return result;
	}


}

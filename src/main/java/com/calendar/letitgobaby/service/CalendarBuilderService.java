package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.util.LunarSolarConverter;
import com.calendar.letitgobaby.vo.DateInfo;
import com.calendar.letitgobaby.vo.Lunar;
import com.calendar.letitgobaby.vo.Solar;
import com.ibm.icu.util.ChineseCalendar;

import org.json.simple.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarBuilderService {

  private final HolidayRepository holidayRepository;
  private final LunarSolarConverter converter;
  
  public JSONArray getYearCalendar(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month); 
		cal.set(year, month - 1, 1); 
		
		int thisMonthLastDate = cal.getActualMaximum(Calendar.DATE);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		JSONArray monthArr = new JSONArray(); 

		int count = 1;
		for (int week = 0; week < 6; week++) {
			ArrayList weekArr = new ArrayList();
			if (count == 1) {
				for (int j = 1; j < dayOfWeek; j++) {
					JSONObject obj = new JSONObject();
					obj.put("00", "toto");
					weekArr.add(obj);
				}
			}

			for (int day = dayOfWeek; day < 8; day++) {
				if (count <= thisMonthLastDate) {
					DateInfo info = dayInfo(year, month, count);
					weekArr.add(info);
					// weekArr.add(dayInfo(year, month, count));
					count++;
				} else {
					weekArr.add("00");
				}
			}

			dayOfWeek = 1;
			monthArr.add(weekArr);
		}

		return monthArr;
  }

	private DateInfo dayInfo(int year, int month, int date) {
		return new DateInfo(
			solarInfo(year, month, date),
			lunarInfo(year, month, date), 
			isHoliday(year, month, date)
		);
	}

	// TODO
	private boolean isHoliday(int year, int month, int date) {
		Lunar lunar = converter.SolarToLunar(new Solar(year, month, date));

		return false;
	}

	private Lunar lunarInfo(int year, int month, int date) {
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
			dateParser(lunar.getLunarYear(), lunar.getLunarMonth(), lunar.getLunarDay())
		);

		return lunar;
	}

	private Solar solarInfo(int year, int month, int date) {
		Solar solar = new Solar(year, month, date);
		solar.setFulldate(dateParser(year, month, date));

		return solar;
	}

	private String dateParser(int year, int month, int date) {
		String result = "";
		result += Integer.toString(year);
		result += month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
		result += date < 10 ? "0" + Integer.toString(date) : Integer.toString(date);

		return result;
	}


  // private JSONObject dayInfo(int year, int month, int date) {
	// 	JSONParser parser = new JSONParser();
	// 	JSONObject obj = new JSONObject();

	// 	obj.put("solar", solarInfo(year, month, date));
	// 	obj.put("lunar", lunarInfo(year, month, date));
	// 	obj.put("isHoliday", isHoliday(year, month, date));

	// 	return obj;
	// }

	// private JSONObject isHoliday(int year, int month, int date) {
	// 	JSONObject obj = new JSONObject();
	// 	Lunar lunar = converter.SolarToLunar(new Solar(year, month, date));

	// 	return obj;
	// }

	// private JSONObject lunarInfo(int year, int month, int date) {
	// 	JSONObject obj = new JSONObject();
	// 	Lunar lunar = converter.SolarToLunar(new Solar(year, month, date));
	// 	LocalDateTime dateStr = LocalDateTime.of(
	// 		lunar.getLunarYear(), 
	// 		lunar.getLunarMonth(), 
	// 		lunar.getLunarDay(), 0,0,0);

	// 	obj.put("fulldate", dateStr.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	// 	obj.put("year", lunar.getLunarYear());
	// 	obj.put("month", lunar.getLunarMonth());
	// 	obj.put("date", lunar.getLunarDay());

	// 	return obj;
	// }

	// private JSONObject solarInfo(int year, int month, int date) {
	// 	JSONObject obj = new JSONObject();
	// 	LocalDateTime dateStr = LocalDateTime.of(year, month, date, 0,0,0);
	// 	obj.put("fulldate", dateStr.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	// 	obj.put("year", year);
	// 	obj.put("month", month);
	// 	obj.put("date", date);

	// 	return obj;
	// }


}

package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.vo.DateInfo;
import com.calendar.letitgobaby.vo.DayOfWeekType;
import com.calendar.letitgobaby.vo.Holiday;
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
  
  public JSONArray getYearCalendar(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1); 
		
		int thisMonthLastDate = cal.getActualMaximum(Calendar.DATE); // 해당 달의 마지막 날
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일 계산

		JSONArray weekArr = new JSONArray(); 

		int count = 1; // 일자 카운트 1일 부터 시작
		for (int week = 0; week < 6; week++) {
			ArrayList dayArr = new ArrayList();
			if (count == 1) {
				for (int j = 1; j < dayOfWeek; j++) {
					JSONObject obj = new JSONObject();
					dayArr.add(obj);
				}
			}

			for (int day = dayOfWeek; day < 8; day++) {
				if (count <= thisMonthLastDate) {
					DateInfo info = dateBuilder(year, month, count);
					for (DayOfWeekType type : DayOfWeekType.values()) {
						if (type.getWeekIndex() == day)  {
							info.setDayOfWeek(type.getValue());
							break;
						}
					}
					dayArr.add(info);
					count++;
				} else {

					dayArr.add("00");
				}
			}

			dayOfWeek = 1; // 1(일요일)로 초기화
			weekArr.add(dayArr);
		}

		return weekArr;
  }

	private DateInfo dateBuilder(int year, int month, int date) {
		Solar solar = solarInfo(year, month, date);
		Lunar lunar = lunarInfo(year, month, date);
		
		return new DateInfo(solar, lunar, holidayInfo(solar, lunar));
	}

	// NEED REFACTORING
	private String holidayInfo(Solar solar, Lunar lunar) {
		Holiday holiday = holidayRepository.findHoliday(
			solar.getSolarMonth(), solar.getSolarDay(), 
			lunar.getLunarMonth(), lunar.getLunarDay()
		);

		if (holiday == null) return "none";

		return holiday.getDateName();
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

}
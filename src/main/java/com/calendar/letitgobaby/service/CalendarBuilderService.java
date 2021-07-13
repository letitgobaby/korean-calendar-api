package com.calendar.letitgobaby.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.calendar.letitgobaby.repository.HolidayRepository;
import com.calendar.letitgobaby.util.LunarSolarConverter;
import com.calendar.letitgobaby.vo.DateInfo;
import com.calendar.letitgobaby.vo.DayOfWeekType;
import com.calendar.letitgobaby.vo.Holiday;
import com.calendar.letitgobaby.vo.Lunar;
import com.calendar.letitgobaby.vo.Solar;

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
		Solar solar = converter.solarInfo(year, month, date);
		Lunar lunar = converter.lunarInfo(year, month, date);
		
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

}